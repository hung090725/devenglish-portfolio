# Báo Cáo Test Trên Thiết Bị Thật (Version 0.9.4-beta)

Tài liệu này lưu trữ danh sách các Test Case áp dụng cho thiết bị thật trong đợt phát hành 0.9.4-beta. Bạn có thể sử dụng màn hình Solo QA trong ứng dụng để tick kết quả và copy xuất ra văn bản.

## Các hạng mục cần kiểm tra (Checklist 36 mục)

1. Install APK
2. First launch
3. Splash
4. Onboarding
5. Level selection
6. Home screen
7. Lessons screen
8. A0 day 1 open
9. TTS button
10. Microphone permission allow
11. Microphone permission deny
12. Speaking practice
13. Quiz answer
14. Mark completed
15. Unlock next day
16. Vocabulary Review
17. Again / Hard / Good / Easy
18. Reminder settings
19. Notification permission allow/deny nếu Android 13+
20. Backup export
21. Copy backup JSON
22. Uninstall app
23. Reinstall app
24. Import backup JSON
25. Verify restored progress
26. Solo QA Content Self-Test
27. Solo QA Backup Self-Test
28. Solo QA Progress Self-Test
29. Export Diagnostics JSON
30. Submit Beta Feedback
31. Offline mode
32. Close app / reopen app
33. Small screen check nếu có
34. A1 locked/unlocked logic
35. A2 locked/unlocked logic
36. B1 locked/unlocked logic

## Format báo cáo
Khi bạn bấm Export Report trong ứng dụng, hãy dán đè dữ liệu vào đây. Nó sẽ có cấu trúc như sau (Ví dụ):

```text
=== REAL DEVICE TEST REPORT ===
Date: 2026-xx-xx HH:mm:ss
App Version: 0.9.4-beta (9)
Device: Samsung SM-A505F
Android API: 30
-------------------------------
SUMMARY: PASS=0 FAIL=0 BLOCKED=0 NOT_TESTED=36
Diagnostics Summary: (Xem tại màn hình Backup Restore)
-------------------------------
[NOT_TESTED] 1. Install APK
[NOT_TESTED] 2. First launch
...
[NOT_TESTED] 36. B1 locked/unlocked logic
```
