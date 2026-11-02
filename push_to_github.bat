@echo off
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
