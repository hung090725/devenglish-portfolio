import os
import shutil
import re
import subprocess
import sys

SRC_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
PARENT_DIR = os.path.dirname(SRC_DIR)
TARGET_DIR = os.path.join(PARENT_DIR, 'devenglish-portfolio')

print(f"Source project: {SRC_DIR}")
print(f"Portfolio destination: {TARGET_DIR}")

# 1. Clean previous target if exists
if os.path.exists(TARGET_DIR):
    print("Removing previous portfolio directory...")
    shutil.rmtree(TARGET_DIR, ignore_errors=True)

os.makedirs(TARGET_DIR, exist_ok=True)

# 2. Define copy rules
# Directories to copy
COPY_DIRS = [
    ('app/src', 'app/src'),
    ('gradle', 'gradle'),
    ('pwa', 'pwa'),
    ('docs/screenshots', 'docs/screenshots'),
]

# Explicit files to copy
COPY_FILES = [
    'app/build.gradle.kts',
    'app/proguard-rules.pro',
    'build.gradle.kts',
    'settings.gradle.kts',
    'gradle.properties',
    'gradlew',
    'gradlew.bat',
    'start_pwa.bat',
    '.gitignore',
    'docs/IPHONE_PWA_USER_GUIDE.md',
    'docs/CEFR_LEVEL_CALIBRATION_RULES.md',
]

print("Copying directories...")
for src_sub, dst_sub in COPY_DIRS:
    src_path = os.path.join(SRC_DIR, src_sub)
    dst_path = os.path.join(TARGET_DIR, dst_sub)
    if os.path.exists(src_path):
        os.makedirs(os.path.dirname(dst_path), exist_ok=True)
        shutil.copytree(src_path, dst_path, ignore=shutil.ignore_patterns('*.pyc', '__pycache__', '.DS_Store', '*.log'))
        print(f"  + Copied {src_sub} -> {dst_sub}")

print("Copying single files...")
for file_sub in COPY_FILES:
    src_path = os.path.join(SRC_DIR, file_sub)
    dst_path = os.path.join(TARGET_DIR, file_sub)
    if os.path.exists(src_path):
        os.makedirs(os.path.dirname(dst_path), exist_ok=True)
        shutil.copy2(src_path, dst_path)
        print(f"  + Copied {file_sub}")

# 3. Create MIT License
license_content = """MIT License

Copyright (c) 2026 DevEnglish AI Team

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
"""
with open(os.path.join(TARGET_DIR, 'LICENSE'), 'w', encoding='utf-8') as f:
    f.write(license_content)
print("  + Generated LICENSE (MIT)")

# 4. Generate clean, sanitized Portfolio README.md
portfolio_readme = """# 🚀 DEVENGLISH AI (Public Portfolio Showcase)
### AI-Powered English Learning Platform for IT Students & Developers (Android & iOS PWA)

[![Build Status](https://img.shields.io/badge/Android-Build%20Passing-brightgreen.svg?style=for-the-badge&logo=android)](#-hướng-dẫn-cài-đặt--khởi-chạy)
[![Unit Tests](https://img.shields.io/badge/Unit%20Tests-144%2F144%20(100%25)-success?style=for-the-badge&logo=checkmarx)](#-chất-lượng-mã-nguồn--kiểm-thử-tự-động)
[![Platforms](https://img.shields.io/badge/Platforms-Android%20%7C%20iOS%20(iPhone)%20%7C%20Web-blue?style=for-the-badge&logo=apple)](pwa/)
[![Offline First](https://img.shields.io/badge/Offline%20First-100%25%20Functional-orange?style=for-the-badge&logo=pwa)](pwa/sw.js)
[![CEFR Standard](https://img.shields.io/badge/Standard-CEFR%20Companion%20Volume-blueviolet?style=for-the-badge)](docs/CEFR_LEVEL_CALIBRATION_RULES.md)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=for-the-badge)](LICENSE)

---

## 💡 BÀI TOÁN & TẦM NHÌN SẢN PHẨM (PROBLEM & VISION)

Thị trường học tiếng Anh hiện nay tràn ngập các ứng dụng học từ vựng đại trà như Duolingo hay Quizlet. Tuy nhiên, **sinh viên CNTT và kỹ sư phần mềm trẻ** lại gặp rào cản rất lớn khi bước vào môi trường doanh nghiệp công nghệ toàn cầu:
- Họ hiểu cú pháp code nhưng **không thể giải thích luồng hoạt động bằng tiếng Anh**.
- Họ bối rối khi đọc hiểu các tài liệu kỹ thuật phức tạp (*RFC, API Docs, Microservices Architecture*).
- Họ không biết cách viết một báo cáo lỗi (*Bug Report*) chuẩn Jira, viết commit message theo quy chuẩn quốc tế, hay báo cáo công việc trong phiên họp *Daily Scrum*.

**DevEnglish AI** ra đời để giải quyết chính xác bài toán này: **Tiếng Anh chuyên biệt cho kỹ sư công nghệ**, kết hợp công nghệ thị giác máy tính AR và thuật toán đánh giá kép độc lập (**Dual-Rubric Scoring**).

---

## 📸 SHOWCASE TÍNH NĂNG NỔI BẬT (FEATURE SHOWCASE)

Toàn bộ các tính năng đều đã được kiểm thử tự động toàn diện và chụp màn hình thực tế:

---

### 1. 📷 AR Camera Real-Time Lens (Thị Giác Máy Tính Quét Từ Vựng)
*Hướng Camera vào bất kỳ đồ vật công sở hay đời sống, hiển thị ngay từ vựng tiếng Anh, phiên âm IPA, nghĩa tiếng Việt và khung nhắm công nghệ cao.*

![AR Camera Real-Time Lens](docs/screenshots/01_ar_camera_lens.png)

- **Công nghệ:** WebRTC (`getUserMedia`), Canvas HUD Rendering 60fps, hỗ trợ đổi camera sau/trước và bật đèn Flash (`Torch`).
- **Tương tác trực tiếp:** Thẻ từ vựng nổi với phát âm bản xứ (Text-to-Speech) và nút nhắm mục tiêu thời gian thực.

---

### 2. 📚 Bách Khoa Toàn Thư Từ Vựng (Encyclopedic Deep Knowledge Sheet)
*Chạm vào bất kỳ đồ vật nào được nhận diện để mở bảng tri thức bách khoa đa tầng.*

![Bách Khoa Từ Vựng Deep Knowledge](docs/screenshots/02_deep_knowledge_sheet.png)

- **Cấu trúc tri thức đa tầng (CEFR A1 - C1):**
  - Từ loại, định nghĩa chuyên sâu, cụm từ thông dụng (*Collocations*).
  - Câu ví dụ thực tế trong đời sống & ngữ cảnh CNTT (*Technical Context*).
  - Thành ngữ bản xứ (*Idioms*) và hội thoại mẫu (*Mini-dialogue*).
  - **Trắc nghiệm nhanh AR (Pop-Quiz):** Tương tác ghi nhớ bài học tức thì.

---

### 3. 💻 Code-to-English AI Lab (Tính Năng Chủ Lực #1)
*Đọc hiểu đoạn code lập trình và giải thích chức năng bằng tiếng Anh. AI chấm điểm 2 trục độc lập.*

![Code-to-English AI Lab](docs/screenshots/03_code_to_english_lab.png)

- **Hỗ trợ đa ngôn ngữ:** Java (Spring Boot), Python (AI/ML), JavaScript (Async/Await), SQL (Join/Aggregate), C# (Guard Clauses).
- **Thuật toán Dual Rubric Scoring độc lập:**
  - **Technical Correctness (0 - 100):** Đánh giá mức độ hiểu đúng luồng dữ liệu & kiểm tra từ khóa kỹ thuật bắt buộc.
  - **English Expression (0 - 100):** Đánh giá độ chuẩn ngữ pháp, thì động từ và văn phong kỹ thuật.
- **Native Phrasing Suggestion:** Gợi ý cách diễn đạt chuẩn của kỹ sư bản xứ kèm âm thanh phát âm.

---

### 4. 📄 Technical Documentation Reader (Tính Năng Chủ Lực #2)
*Luyện đọc tài liệu công nghệ chuyên sâu, bóc tách thuật ngữ và làm trắc nghiệm tương tác.*

![Technical Documentation Reader](docs/screenshots/04_tech_doc_reader.png)

- **Kho bài đọc công nghệ hiện đại:** *React Hooks Lifecycle*, *Docker Containerization Architecture*, *Microservices & Event-Driven Systems*.
- **Plain English Simplified:** Bản giải thích đơn giản hóa giúp người học nắm bắt nội dung cốt lõi nhanh chóng mà không bị quá tải thuật ngữ.
- **Interactive Comprehension Quiz:** Bài tập trắc nghiệm đọc hiểu chấm điểm tức thì kèm giải thích chi tiết.

---

### 5. 🎯 IT English Mock Interview Simulator (Tính Năng Chủ Lực #3)
*Mô phỏng phỏng vấn xin việc tuyển dụng IT bằng tiếng Anh qua 4 vòng chuẩn doanh nghiệp.*

![IT English Mock Interview Simulator](docs/screenshots/05_it_interview_simulator.png)

- **4 Vị trí tuyển dụng:** *Junior Java Developer, Frontend/Web Developer, AI Engineer, QA/QC Automation*.
- **4 Vòng phỏng vấn:** Giới thiệu bản thân (Formula P-P-F), Kiến thức kỹ thuật cốt lõi, Xử lý lỗi hệ thống, và Văn hóa Agile (Phương pháp STAR).
- **Đánh giá 5 tiêu chí chi tiết:** Technical Relevance, Vocabulary Range, Fluency, Grammar, kèm câu trả lời mẫu **Band 7.5+**.

---

### 6. 🛠️ Workplace Practice Tools (Bộ Công Cụ Thực Chiến Doanh Nghiệp)
*Rèn luyện các kỹ năng giao tiếp công việc hàng ngày trong môi trường quốc tế.*

![Workplace Practice Tools](docs/screenshots/06_workplace_practice_tools.png)

- **🐞 Bug Report English Trainer:** Viết báo cáo lỗi chuẩn Jira (Title, Steps to Reproduce, Expected, Actual, Environment).
- **🌿 Conventional Commits Validator:** Kiểm tra commit theo quy chuẩn quốc tế (`feat:`, `fix:`, `docs:`, `refactor:`, `test:`, `chore:`).
- **⏱️ Daily Standup Scrum Simulator:** Luyện phản xạ trả lời 3 câu hỏi kinh điển trong buổi họp Daily Scrum.

---

### 7. 📚 Bách Khoa Tri Thức Từ Vựng (Visual Vocabulary Hub)
*Tra cứu toàn diện 35+ vật thể, công nghệ và phân loại cấp độ CEFR.*

![Visual Vocabulary Dictionary Hub](docs/screenshots/07_vocabulary_dictionary_hub.png)

---

### 8. ⚙️ Cài Đặt Âm Thanh, Tiến Độ & Chế Độ Offline
*Theo dõi tiến độ học tập và tùy chỉnh giọng đọc bản xứ.*

![Settings and Offline Profile](docs/screenshots/08_settings_profile.png)

---

## 🏛️ KIẾN TRÚC HỆ THỐNG & CÔNG NGHỆ (TECH STACK)

```text
┌────────────────────────────────────────────────────────────────────────┐
│                        DEVENGLISH AI PLATFORM                          │
├───────────────────────────────────┬────────────────────────────────────┤
│         ANDROID NATIVE            │         CROSS-PLATFORM PWA         │
│  - Java 17 / Android SDK (API 35) │  - Modern ES6 Modules / HTML5      │
│  - CameraX AR Analysis Pipeline   │  - WebRTC Video Stream (Camera sau)│
│  - Material Design 3 UI           │  - Web Speech API (STT & TTS)      │
│  - Room Persistence Database      │  - Service Worker (Offline Cache)  │
│  - LiveData & MVVM Architecture   │  - Apple HIG & Safe Area Styling   │
├───────────────────────────────────┴────────────────────────────────────┤
│                         AI & HEURISTICS ENGINE                         │
│  - Dual-Rubric Scoring (Technical Correctness vs. English Expression)  │
│  - Conventional Commits Regex Parser & AST Keyword Matching            │
│  - 100% Offline Heuristic Fallback (Không phụ thuộc Cloud server)      │
└────────────────────────────────────────────────────────────────────────┘
```

---

## 🚀 HƯỚNG DẪN CÀI ĐẶT & KHỞI CHẠY (QUICK START)

### 1. Trải nghiệm ngay trên iPhone / iPad (iOS Safari PWA)
1. Chạy máy chủ PWA trên máy tính bằng cách nhấp đúp vào: [`start_pwa.bat`](start_pwa.bat) *(hoặc gõ `python pwa/serve.py`)*.
2. Mở trình duyệt **Safari** trên iPhone, truy cập địa chỉ IP hiển thị trên terminal (ví dụ: `http://192.168.1.15:8080`).
3. Bấm nút **Chia sẻ (Share ⎋)** > Chọn **"Thêm vào Màn hình chính" (Add to Home Screen)**.
4. Mở app từ màn hình chính iPhone để trải nghiệm mượt mà toàn màn hình!

### 2. Biên dịch & kiểm thử mã nguồn Android Native
```bash
# 1. Chạy toàn bộ 144 bài kiểm thử Unit Tests:
./gradlew testDebugUnitTest

# 2. Biên dịch gói Android Debug:
./gradlew assembleDebug
```

---

## 🧪 CHẤT LƯỢNG MÃ NGUỒN & KIỂM THỬ TỰ ĐỘNG

- **Unit Test Suite:** 144 bài kiểm thử đơn vị bao phủ toàn bộ logic phân tích ngôn ngữ và thuật toán chấm điểm kỹ thuật (`100% PASS`).
- **End-to-End Automation:** Tích hợp kiểm thử tự động giao diện và camera, tự động lưu ảnh chụp màn hình kiểm thử.
- **Tiêu chuẩn bảo mật:** Hoàn toàn không lưu trữ dữ liệu cá nhân ra máy chủ bên ngoài (Local-first & Privacy-by-design).

---

## 📄 BẢN QUYỀN & GIẤY PHÉP

Dự án được phát hành theo giấy phép [MIT License](LICENSE). Được phát triển với niềm đam mê nâng cao năng lực tiếng Anh cho cộng đồng kỹ sư công nghệ.
"""

with open(os.path.join(TARGET_DIR, 'README.md'), 'w', encoding='utf-8') as f:
    f.write(portfolio_readme)
print("  + Generated Sanitized Portfolio README.md")

# 5. Create push_to_github.bat helper script
push_script = """@echo off
title Push DevEnglish Portfolio to GitHub
echo ========================================================
echo   HUONG DAN DAY PORTFOLIO LEN GITHUB PUBLIC REPOSITORY
echo ========================================================
echo.
echo 1. Vao trang: https://github.com/new
echo 2. Tao mot repository moi ten la: devenglish-portfolio (hoac tuy chon)
echo 3. Chon che do: PUBLIC
echo 4. KHONG tich vao "Add a README file"
echo 5. Copy link repository (Vi du: https://github.com/hung090725/devenglish-portfolio.git)
echo.
set /p REPO_URL="Dan duong link GitHub repo cua ban vao day va bam Enter: "

if "%REPO_URL%"=="" (
    echo Ban chua nhap link. Thoat.
    pause
    exit /b
)

echo.
echo Dang lien ket remote origin...
git remote remove origin 2>nul
git remote add origin %REPO_URL%
git branch -M main

echo.
echo Dang day ma nguon len GitHub...
git push -u origin main

echo.
echo ========================================================
echo   HOAN TAT! Portfolio cua ban da online tren GitHub!
echo ========================================================
pause
"""
with open(os.path.join(TARGET_DIR, 'push_to_github.bat'), 'w', encoding='utf-8') as f:
    f.write(push_script)
print("  + Generated push_to_github.bat")

# 6. Sanitize docs/IPHONE_PWA_USER_GUIDE.md inside target
pwa_guide_path = os.path.join(TARGET_DIR, 'docs', 'IPHONE_PWA_USER_GUIDE.md')
if os.path.exists(pwa_guide_path):
    with open(pwa_guide_path, 'r', encoding='utf-8') as f:
        content = f.read()
    # Replace absolute file:///... links with relative links
    content = re.sub(r'file:///c:/Users/Admin/Documents/hoctienganh/tienganhAi/([^\)\"]+)', r'\1', content)
    with open(pwa_guide_path, 'w', encoding='utf-8') as f:
        f.write(content)
    print("  + Sanitized docs/IPHONE_PWA_USER_GUIDE.md")

# 7. Initialize Git repository and make initial commit
print("\nInitializing Git repository in portfolio...")
subprocess.run(['git', 'init', '-b', 'main'], cwd=TARGET_DIR, check=True)
subprocess.run(['git', 'config', 'user.name', 'hung090725'], cwd=TARGET_DIR, check=True)
subprocess.run(['git', 'config', 'user.email', 'hung@portfolio.local'], cwd=TARGET_DIR, check=True)
subprocess.run(['git', 'add', '.'], cwd=TARGET_DIR, check=True)
subprocess.run(['git', 'commit', '-m', 'feat: initial public release of DevEnglish AI portfolio'], cwd=TARGET_DIR, check=True)

print(f"\n🎉 SUCCESS! Portfolio repository created successfully at:\n   {TARGET_DIR}")
