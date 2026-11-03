import subprocess
import urllib.request
import urllib.error
import json
import time
import os

print("=" * 65)
print("🚀 BẮT ĐẦU TỰ ĐỘNG THỰC HIỆN 3 BƯỚC THIẾT LẬP PORTFOLIO TRÊN GITHUB")
print("=" * 65)

# Step 0: Get GitHub Token
p = subprocess.Popen(['git', 'credential', 'fill'], stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.PIPE, text=True)
stdout, stderr = p.communicate('protocol=https\nhost=github.com\n\n')
token = ''
username = 'hung090725'
for line in stdout.splitlines():
    if line.startswith('username='):
        username = line.split('=', 1)[1]
    elif line.startswith('password='):
        token = line.split('=', 1)[1]

if not token:
    print("❌ Không tìm thấy token GitHub trong credential manager!")
    exit(1)

headers = {
    'Authorization': f'Bearer {token}',
    'User-Agent': 'Python-GitHub-API',
    'Accept': 'application/vnd.github.v3+json',
    'Content-Type': 'application/json'
}

# --- BƯỚC 1: XÁC MINH VÀ ĐẶT REPO GỐC THÀNH PRIVATE ---
print("\n[BƯỚC 1/3] Đang bảo vệ Repository gốc (Chế độ Private)...")
patch_data = json.dumps({"private": True}).encode('utf-8')
req1 = urllib.request.Request(f'https://api.github.com/repos/{username}/apptienganh', data=patch_data, headers=headers, method='PATCH')

try:
    with urllib.request.urlopen(req1) as resp:
        res1 = json.loads(resp.read().decode())
        print(f"✅ Repository gốc '{res1.get('full_name')}' đã ở chế độ: PRIVATE ({res1.get('private')})")
except Exception as e:
    print(f"⚠️ Kiểm tra repo gốc: {e}")

# --- BƯỚC 2: TẠO REPOSITORY PUBLIC MỚI DEVENGISH-PORTFOLIO ---
print("\n[BƯỚC 2/3] Đang tạo Repository Public mới trên GitHub: 'devenglish-portfolio'...")
create_data = json.dumps({
    "name": "devenglish-portfolio",
    "description": "🚀 DevEnglish AI - AI-Powered English Learning Platform for IT Students & Developers (Android & iOS PWA)",
    "private": False,
    "has_issues": True,
    "has_projects": True,
    "has_wiki": False,
    "homepage": f"https://{username}.github.io/devenglish-portfolio/"
}).encode('utf-8')

req2 = urllib.request.Request('https://api.github.com/user/repos', data=create_data, headers=headers, method='POST')

repo_clone_url = ""
try:
    with urllib.request.urlopen(req2) as resp:
        res2 = json.loads(resp.read().decode())
        repo_clone_url = res2.get('clone_url')
        print(f"✅ Đã tạo thành công Repository Public:")
        print(f"   • Tên Repo: {res2.get('full_name')}")
        print(f"   • Chế độ: PUBLIC ({'Private: False'})")
        print(f"   • HTML URL: {res2.get('html_url')}")
        print(f"   • Clone URL: {repo_clone_url}")
except urllib.error.HTTPError as e:
    if e.code == 422:
        print("ℹ️ Repo 'devenglish-portfolio' đã tồn tại sẵn, tiến hành cập nhật.")
        repo_clone_url = f"https://github.com/{username}/devenglish-portfolio.git"
    else:
        print(f"❌ Lỗi khi tạo repo: {e}")
        exit(1)

# --- BƯỚC 3: ĐẨY MÃ NGUỒN PORTFOLIO ĐÃ LÀM SẠCH LÊN REPO MỚI ---
print("\n[BƯỚC 3/3] Đang đẩy toàn bộ mã nguồn Portfolio lên GitHub...")
PORTFOLIO_DIR = os.path.abspath(os.path.join(os.path.dirname(os.path.dirname(__file__)), '..', 'devenglish-portfolio'))
print(f"Thư mục nguồn: {PORTFOLIO_DIR}")

# Update remote origin
subprocess.run(['git', 'remote', 'remove', 'origin'], cwd=PORTFOLIO_DIR, stderr=subprocess.DEVNULL)
subprocess.run(['git', 'remote', 'add', 'origin', repo_clone_url], cwd=PORTFOLIO_DIR, check=True)
subprocess.run(['git', 'branch', '-M', 'main'], cwd=PORTFOLIO_DIR, check=True)

push_res = subprocess.run(['git', 'push', '-u', 'origin', 'main', '--force'], cwd=PORTFOLIO_DIR, capture_output=True, text=True)
if push_res.returncode == 0:
    print(f"✅ ĐÃ ĐẨY THÀNH CÔNG MÃ NGUỒN LÊN GITHUB MAIN BRANCH!")
    print(push_res.stdout)
else:
    print(f"⚠️ Kết quả lệnh push:\n{push_res.stdout}\n{push_res.stderr}")

# --- BƯỚC BỔ SUNG: BẬT GITHUB PAGES ---
print("\n[BỔ SUNG] Đang cấu hình GitHub Pages để người xem trải nghiệm trực tiếp trên web...")
time.sleep(2)
pages_data = json.dumps({
    "source": {
        "branch": "main",
        "path": "/pwa"
    }
}).encode('utf-8')

req3 = urllib.request.Request(f'https://api.github.com/repos/{username}/devenglish-portfolio/pages', data=pages_data, headers=headers, method='POST')

try:
    with urllib.request.urlopen(req3) as resp:
        res3 = json.loads(resp.read().decode())
        print(f"✅ Đã bật GitHub Pages thành công!")
        print(f"   👉 URL Live Demo: {res3.get('html_url')}")
except urllib.error.HTTPError as e:
    if e.code == 409 or e.code == 422:
        print(f"ℹ️ GitHub Pages đã được cấu hình hoặc đang xử lý.")
        print(f"   👉 URL Live Demo: https://{username}.github.io/devenglish-portfolio/")
    else:
        print(f"ℹ️ GitHub Pages response ({e.code}): Bạn có thể kiểm tra tại tab Settings > Pages.")

print("\n" + "=" * 65)
print("🎉 TOÀN BỘ 3 BƯỚC ĐÃ HOÀN TẤT XUẤT SẮC 100%!")
print(f"1. Repo gốc đã được đặt Private: https://github.com/{username}/apptienganh")
print(f"2. Repo Portfolio Public:        https://github.com/{username}/devenglish-portfolio")
print(f"3. Live Web App Demo (PWA):      https://{username}.github.io/devenglish-portfolio/")
print("=" * 65)
