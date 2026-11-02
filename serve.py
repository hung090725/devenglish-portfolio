#!/usr/bin/env python3
"""
Local Dev Server for DevEnglish AI PWA
Serves on 0.0.0.0:8080 with proper MIME types for PWA Service Workers and ES modules.
"""
import http.server
import socket
import socketserver
import os
import sys

PORT = 8080
DIRECTORY = os.path.dirname(os.path.abspath(__file__))

def get_local_ip():
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        s.connect(("8.8.8.8", 80))
        ip = s.getsockname()[0]
        s.close()
        return ip
    except Exception:
        return "127.0.0.1"

class PwaHandler(http.server.SimpleHTTPRequestHandler):
    def __init__(self, *args, **kwargs):
        super().__init__(*args, directory=DIRECTORY, **kwargs)

    def end_headers(self):
        # Enable CORS and Cache headers for PWA
        self.send_header('Access-Control-Allow-Origin', '*')
        self.send_header('Cache-Control', 'no-cache')
        super().end_headers()

    def guess_type(self, path):
        if path.endswith('.js') or path.endswith('.mjs'):
            return 'application/javascript'
        if path.endswith('.json') or path.endswith('manifest.json'):
            return 'application/manifest+json'
        if path.endswith('.css'):
            return 'text/css'
        return super().guess_type(path)

def run():
    local_ip = get_local_ip()
    print("=" * 65)
    print("🚀 DEVENGLISH AI - PWA SERVER ĐÃ KHỞI CHẠY!")
    print("=" * 65)
    print(f"👉 Trên máy tính này:     http://localhost:{PORT}")
    print(f"📱 Trên iPhone (cùng Wifi): http://{local_ip}:{PORT}")
    print("=" * 65)
    print("💡 MẸO SỬ DỤNG TRÊN IPHONE:")
    print(" 1. Kết nối iPhone vào cùng mạng Wi-Fi với máy tính.")
    print(f" 2. Mở trình duyệt Safari trên iPhone và gõ: http://{local_ip}:{PORT}")
    print(" 3. Bấm nút [Chia sẻ ⎋] > Chọn 'Thêm vào Màn hình chính' (Add to Home Screen).")
    print(" 4. Mở app từ màn hình chính iPhone để dùng full màn hình như app App Store thật!")
    print(" 5. LƯU Ý CAMERA:")
    print("    • iOS Safari yêu cầu HTTPS hoặc localhost để cấp quyền Camera.")
    print("    • Bạn có thể dùng Cloudflare Tunnel miễn phí (gõ: npx cloudflared tunnel --url http://localhost:8080)")
    print("    • Hoặc đẩy thư mục pwa lên GitHub Pages / Vercel là có link HTTPS tự động!")
    print("=" * 65)
    print("Nhấn Ctrl + C để dừng máy chủ.\n")

    socketserver.ThreadingTCPServer.allow_reuse_address = True
    with socketserver.ThreadingTCPServer(("0.0.0.0", PORT), PwaHandler) as httpd:
        try:
            httpd.serve_forever()
        except KeyboardInterrupt:
            print("\nĐã tắt máy chủ.")

if __name__ == '__main__':
    run()
