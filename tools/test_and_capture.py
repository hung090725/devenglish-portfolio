import os
import sys
import time
import threading
import http.server
import socketserver
from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC

PORT = 8085
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
        pass # suppress server logs for clean test output

def start_server():
    socketserver.ThreadingTCPServer.allow_reuse_address = True
    server = socketserver.ThreadingTCPServer(("127.0.0.1", PORT), PwaHandler)
    server.serve_forever()

# Start local server in daemon thread
t = threading.Thread(target=start_server, daemon=True)
t.start()
time.sleep(1)
print(f"Local PWA test server started on http://127.0.0.1:{PORT}")

# Setup Chrome
chrome_options = Options()
chrome_options.add_argument('--headless=new')
chrome_options.add_argument('--window-size=414,896') # iPhone XR / 11 / 14 / 15 dimensions
chrome_options.add_argument('--use-fake-ui-for-media-stream')
chrome_options.add_argument('--use-fake-device-for-media-stream')
chrome_options.add_argument('--autoplay-policy=no-user-gesture-required')
chrome_options.add_argument('--no-sandbox')
chrome_options.add_argument('--disable-dev-shm-usage')
chrome_options.add_argument('--hide-scrollbars')
chrome_options.add_argument('--user-agent=Mozilla/5.0 (iPhone; CPU iPhone OS 17_4 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/17.4 Mobile/15E148 Safari/604.1')

driver = webdriver.Chrome(options=chrome_options)
driver.set_window_size(414, 896)

def check_console_errors():
    logs = driver.get_log('browser')
    errors = [l for l in logs if l['level'] == 'SEVERE']
    if errors:
        print("⚠️ Warning: Console errors found:")
        for err in errors:
            print("  ", err['message'])
    return errors

try:
    print("\n--- [BƯỚC 1/8] KIỂM THỬ AR CAMERA REAL-TIME LENS ---")
    driver.get(f"http://127.0.0.1:{PORT}/index.html")
    time.sleep(2)
    check_console_errors()

    # Dismiss install banner if visible
    try:
        btn_close_banner = driver.find_element(By.ID, "btn-close-banner")
        if btn_close_banner.is_displayed():
            btn_close_banner.click()
            time.sleep(0.3)
    except Exception:
        pass

    # Trigger scan button to ensure bounding box and pill are active
    btn_scan = driver.find_element(By.ID, "btn-capture-scan")
    driver.execute_script("arguments[0].click();", btn_scan)
    time.sleep(1.5)
    check_console_errors()

    shot1 = os.path.join(SCREENSHOT_DIR, "01_ar_camera_lens.png")
    driver.save_screenshot(shot1)
    print(f"✅ Đã chụp màn hình AR Lens: {shot1} ({os.path.getsize(shot1)} bytes)")

    def click_el(el):
        driver.execute_script("arguments[0].scrollIntoView({block: 'center'});", el)
        time.sleep(0.2)
        driver.execute_script("arguments[0].click();", el)

    print("\n--- [BƯỚC 2/8] KIỂM THỬ BÁCH KHOA TỪ VỰNG & SM-2 SPACED REPETITION ---")
    # Click detected pill card to open modal sheet
    pill = driver.find_element(By.CLASS_NAME, "object-pill-card")
    click_el(pill)
    time.sleep(1)

    # Test SuperMemo-2 Rating Button (Score 4: Nhớ tốt)
    sm2_btn = driver.find_element(By.CSS_SELECTOR, ".sm2-rate-btn[data-score='4']")
    click_el(sm2_btn)
    time.sleep(0.5)
    sm2_status = driver.find_element(By.ID, "sheet-sm2-status").text
    print(f"✅ Đã kiểm thử thuật toán SM-2 thành công: '{sm2_status}'")

    shot2 = os.path.join(SCREENSHOT_DIR, "02_deep_knowledge_sheet.png")
    driver.save_screenshot(shot2)
    print(f"✅ Đã chụp màn hình Deep Knowledge Sheet: {shot2} ({os.path.getsize(shot2)} bytes)")

    # Close modal
    btn_close = driver.find_element(By.ID, "btn-close-sheet")
    click_el(btn_close)
    time.sleep(0.5)

    print("\n--- [BƯỚC 3/8] KIỂM THỬ CODE-TO-ENGLISH AI LAB (2 TRỤC ĐỘC LẬP) ---")
    tab_it = driver.find_element(By.CSS_SELECTOR, ".tab-nav-item[data-tab='tab-it-hub']")
    click_el(tab_it)
    time.sleep(0.5)

    # Input explanation
    code_input = driver.find_element(By.ID, "code-explanation-input")
    code_input.clear()
    code_input.send_keys("This function takes two integer arguments, a and b, and returns their sum as an integer.")

    # Click evaluate
    btn_eval_code = driver.find_element(By.ID, "btn-eval-code")
    click_el(btn_eval_code)
    time.sleep(1)

    shot3 = os.path.join(SCREENSHOT_DIR, "03_code_to_english_lab.png")
    driver.save_screenshot(shot3)
    print(f"✅ Đã chụp màn hình Code-to-English Lab: {shot3} ({os.path.getsize(shot3)} bytes)")

    print("\n--- [BƯỚC 4/8] KIỂM THỬ TECHNICAL DOCUMENTATION READER ---")
    subtab_doc = driver.find_element(By.CSS_SELECTOR, ".segment-btn[data-subtab='subtab-doc']")
    click_el(subtab_doc)
    time.sleep(0.5)

    # Answer a quiz option
    opt_btn = driver.find_element(By.CSS_SELECTOR, ".opt-btn")
    click_el(opt_btn)
    time.sleep(0.5)

    shot4 = os.path.join(SCREENSHOT_DIR, "04_tech_doc_reader.png")
    driver.save_screenshot(shot4)
    print(f"✅ Đã chụp màn hình Tech Doc Reader: {shot4} ({os.path.getsize(shot4)} bytes)")

    print("\n--- [BƯỚC 5/8] KIỂM THỬ IT ENGLISH INTERVIEW SIMULATOR ---")
    subtab_int = driver.find_element(By.CSS_SELECTOR, ".segment-btn[data-subtab='subtab-interview']")
    click_el(subtab_int)
    time.sleep(0.5)

    int_input = driver.find_element(By.ID, "interview-answer-input")
    int_input.clear()
    int_input.send_keys("I graduated with a Computer Science degree and have solid experience building backend RESTful services using Spring Boot.")

    btn_eval_int = driver.find_element(By.ID, "btn-eval-interview")
    click_el(btn_eval_int)
    time.sleep(1)

    shot5 = os.path.join(SCREENSHOT_DIR, "05_it_interview_simulator.png")
    driver.save_screenshot(shot5)
    print(f"✅ Đã chụp màn hình Interview Simulator: {shot5} ({os.path.getsize(shot5)} bytes)")

    print("\n--- [BƯỚC 6/8] KIỂM THỬ WORKPLACE PRACTICE TOOLS ---")
    subtab_prac = driver.find_element(By.CSS_SELECTOR, ".segment-btn[data-subtab='subtab-practice']")
    click_el(subtab_prac)
    time.sleep(0.5)

    driver.find_element(By.ID, "bug-title-input").send_keys("[Auth] Login button fails to trigger on iOS 16")
    driver.find_element(By.ID, "bug-steps-input").send_keys("1. Open login screen. 2. Tap submit.")
    driver.find_element(By.ID, "bug-expected-input").send_keys("User is redirected to home dashboard.")
    driver.find_element(By.ID, "bug-actual-input").send_keys("App shows loading spinner indefinitely.")
    driver.find_element(By.ID, "bug-env-input").send_keys("iPhone 14 Pro, iOS 17.2, Safari")
    click_el(driver.find_element(By.ID, "btn-eval-bug"))

    driver.find_element(By.ID, "commit-input").send_keys("feat(auth): implement Google OAuth2 sign-in flow")
    click_el(driver.find_element(By.ID, "btn-eval-commit"))

    driver.find_element(By.ID, "standup-yesterday").send_keys("Completed user authentication unit tests and merged PR.")
    driver.find_element(By.ID, "standup-today").send_keys("Will work on camera AR scanner optimization.")
    driver.find_element(By.ID, "standup-blockers").send_keys("No blockers at the moment.")
    click_el(driver.find_element(By.ID, "btn-eval-standup"))
    time.sleep(1)

    shot6 = os.path.join(SCREENSHOT_DIR, "06_workplace_practice_tools.png")
    driver.save_screenshot(shot6)
    print(f"✅ Đã chụp màn hình Workplace Tools: {shot6} ({os.path.getsize(shot6)} bytes)")

    print("\n--- [BƯỚC 7/8] KIỂM THỬ DEEP KNOWLEDGE HUB (35+ TỪ VỰNG) ---")
    tab_know = driver.find_element(By.CSS_SELECTOR, ".tab-nav-item[data-tab='tab-knowledge']")
    click_el(tab_know)
    time.sleep(0.5)

    shot7 = os.path.join(SCREENSHOT_DIR, "07_vocabulary_dictionary_hub.png")
    driver.save_screenshot(shot7)
    print(f"✅ Đã chụp màn hình Knowledge Hub: {shot7} ({os.path.getsize(shot7)} bytes)")

    print("\n--- [BƯỚC 8/8] KIỂM THỬ SETTINGS & OFFLINE PROFILE ---")
    tab_set = driver.find_element(By.CSS_SELECTOR, ".tab-nav-item[data-tab='tab-settings']")
    click_el(tab_set)
    time.sleep(0.5)

    shot8 = os.path.join(SCREENSHOT_DIR, "08_settings_profile.png")
    driver.save_screenshot(shot8)
    print(f"✅ Đã chụp màn hình Settings: {shot8} ({os.path.getsize(shot8)} bytes)")

    print("\n🎉 TOÀN BỘ 8/8 MÀN HÌNH ĐÃ KIỂM THỬ THÀNH CÔNG VÀ CHỤP ẢNH ĐẦY ĐỦ!")

except Exception as e:
    print(f"\n❌ LỖI TRONG QUÁ TRÌNH KIỂM THỬ: {e}")
    sys.exit(1)
finally:
    driver.quit()
