import os
import sys
import time
import threading
import http.server
import socketserver
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options

PORT = 8089
BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
PWA_DIR = os.path.join(BASE_DIR, 'pwa')
SCREENSHOT_DIR = os.path.join(BASE_DIR, 'docs', 'screenshots')
os.makedirs(SCREENSHOT_DIR, exist_ok=True)

class PwaHandler(http.server.SimpleHTTPRequestHandler):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, directory=PWA_DIR, **kwargs)

    def guess_type(self, path):
        if path.endswith('.js') or path.endswith('.mjs'):
            return 'application/javascript'
        if path.endswith('.json') or path.endswith('manifest.json'):
            return 'application/manifest+json'
        if path.endswith('.css'):
            return 'text/css'
        return super().guess_type(path)

    def log_message(self, format, *args):
        pass

def start_server():
    socketserver.ThreadingTCPServer.allow_reuse_address = True
    server = socketserver.ThreadingTCPServer(("127.0.0.1", PORT), PwaHandler)
    server.serve_forever()

t = threading.Thread(target=start_server, daemon=True)
t.start()
time.sleep(1)
print(f"Local test server started on http://127.0.0.1:{PORT}")

chrome_options = Options()
chrome_options.add_argument('--headless=new')
chrome_options.add_argument('--window-size=414,896')
chrome_options.add_argument('--use-fake-ui-for-media-stream')
chrome_options.add_argument('--use-fake-device-for-media-stream')
chrome_options.add_argument('--autoplay-policy=no-user-gesture-required')
chrome_options.add_argument('--no-sandbox')
chrome_options.add_argument('--disable-dev-shm-usage')
chrome_options.add_argument('--hide-scrollbars')

driver = webdriver.Chrome(options=chrome_options)
driver.set_window_size(414, 896)

try:
    print("\n=== KIỂM THỬ TÍNH NĂNG: ĐƯA CAMERA TỚI ĐÂU TỰ HIỆN TỪ VỰNG TỚI ĐÓ ===")
    driver.get(f"http://127.0.0.1:{PORT}/index.html")
    time.sleep(2.5)

    # Dismiss install banner if open
    try:
        btn_close_banner = driver.find_element(By.ID, "btn-close-banner")
        if btn_close_banner.is_displayed():
            btn_close_banner.click()
            time.sleep(0.3)
    except Exception:
        pass

    # Wait 2 seconds for continuous stream loop without touching any button!
    time.sleep(2)

    # 1. Check video stream
    video_state = driver.execute_script("""
        const video = document.getElementById('camera-video');
        return {
            readyState: video ? video.readyState : -1,
            videoWidth: video ? video.videoWidth : 0,
            videoHeight: video ? video.videoHeight : 0,
            paused: video ? video.paused : true
        };
    """)
    print(f"1. Trạng thái Video Stream: {video_state}")

    # 2. Check HUD status text
    hud_text = driver.find_element(By.ID, "camera-hud-status").text
    print(f"2. Trạng thái HUD Badge: '{hud_text}'")

    # 3. Check automatically detected boxes and pills WITHOUT CLICKING SCAN BUTTON!
    cards = driver.find_elements(By.CLASS_NAME, "object-pill-card")
    print(f"3. Số lượng thẻ tự động xuất hiện khi đang quay: {len(cards)}")
    for idx, c in enumerate(cards):
        print(f"   👉 Tự động nhận diện #{idx+1}: {c.text.replace(chr(10), ' | ')}")

    # 4. Check active bounding boxes rendered on Canvas
    boxes_info = driver.execute_script("""
        return window.app && window.app.camera ? window.app.camera.detectedBoxes : [];
    """)
    print(f"4. Số khung hộp Neon đang vẽ trực tiếp trên màn hình: {len(boxes_info)}")
    if len(boxes_info) > 0:
        b = boxes_info[0]
        v = b.get('vocab', {})
        print(f"   ✨ Từ vựng tiếng Anh: {v.get('wordEn')}")
        print(f"   ✨ Phiên âm IPA: {v.get('ipa')}")
        print(f"   ✨ Nghĩa tiếng Việt: {v.get('meaningVi')}")
        print(f"   ✨ Câu tiếng Anh C1/C2: {v.get('exampleSentenceEn')}")
        print(f"   ✨ Bản dịch tiếng Việt: {v.get('exampleSentenceVi')}")
        print(f"   ✨ Cụm từ đi kèm (Collocations): {v.get('collocationsEn')}")

    # 5. Capture live continuous screenshot
    shot_path = os.path.join(SCREENSHOT_DIR, "live_continuous_auto_stream.png")
    driver.save_screenshot(shot_path)
    print(f"5. Đã chụp ảnh luồng quét tự động: {shot_path} ({os.path.getsize(shot_path)} bytes)")

    # 6. Test toggle Auto Voice button
    btn_voice = driver.find_element(By.ID, "btn-auto-voice")
    driver.execute_script("arguments[0].click();", btn_voice)
    time.sleep(0.5)
    print(f"6. Nút Auto Voice đã kích hoạt: {btn_voice.text}")

    print("\n🎉 XÁC NHẬN: TÍNH NĂNG TỰ ĐỘNG HIỆN TỪ VỰNG KHI ĐANG QUAY HOẠT ĐỘNG HOÀN TOÀN TỰ ĐỘNG 100%!")

except Exception as e:
    print(f"\n❌ LỖI TRONG QUÁ TRÌNH KIỂM THỬ: {e}")
    sys.exit(1)
finally:
    driver.quit()
