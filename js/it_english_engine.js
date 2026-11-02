/**
 * Core Brain Engine for DEVENGLISH AI (Web & iPhone PWA)
 * Faithful JavaScript port of ItEnglishEngine.java
 */

export class ItEnglishEngine {

  /**
   * Evaluates Code-to-English explanation with TWO INDEPENDENT SCORES:
   * 1) Technical Correctness (Độ chính xác kỹ thuật)
   * 2) English Expression (Chất lượng tiếng Anh)
   */
  static evaluateCodeExplanation(snippet, userExplanation) {
    if (!userExplanation || !userExplanation.trim()) {
      return {
        technicalScore: 0,
        englishScore: 0,
        technicalFeedback: "Bạn chưa nhập lời giải thích cho đoạn code.",
        englishFeedback: "Vui lòng diễn đạt bằng tiếng Anh.",
        nativePhrasingSample: snippet.idealExplanationEn,
        matchedTerms: [],
        missingTerms: snippet.requiredKeyTerms || []
      };
    }

    const lower = userExplanation.toLowerCase().trim();
    const matchedTerms = [];
    const missingTerms = [];

    const reqTerms = snippet.requiredKeyTerms || [];
    for (const term of reqTerms) {
      if (lower.includes(term.toLowerCase())) {
        matchedTerms.add ? matchedTerms.add(term) : matchedTerms.push(term);
      } else {
        missingTerms.add ? missingTerms.add(term) : missingTerms.push(term);
      }
    }

    // --- 1. Technical Score Calculation (0 - 100) ---
    let techScore = 40; // Base score for attempting
    const matchRatio = reqTerms.length === 0 ? 1.0 : matchedTerms.length / reqTerms.length;
    techScore += Math.round(matchRatio * 50);

    // Technical heuristics (action verbs)
    const hasActionVerb = lower.includes("return") || lower.includes("take") || lower.includes("fetch")
      || lower.includes("filter") || lower.includes("validate") || lower.includes("calculate");
    if (hasActionVerb) techScore += 10;
    techScore = Math.min(100, Math.max(10, techScore));

    let techFeedback = "";
    if (techScore >= 85) {
      techFeedback = "✅ Chính xác tuyệt đối về logic lập trình! Bạn đã nêu đúng vai trò tham số, luồng xử lý và kết quả đầu ra.";
    } else if (techScore >= 60) {
      techFeedback = "⚠️ Bạn đã nắm được ý chính của đoạn code, tuy nhiên còn thiếu một số chi tiết kỹ thuật quan trọng: " + missingTerms.join(", ");
    } else {
      techFeedback = "❌ Lời giải thích chưa thể hiện rõ chức năng lập trình cốt lõi. Hãy chú ý đến giá trị trả về và các tham số đầu vào.";
    }

    // --- 2. English Score Calculation (0 - 100) ---
    let englishScore = 40;
    const words = userExplanation.trim().split(/\s+/);
    if (words.length >= 8) englishScore += 15;
    if (words.length >= 15) englishScore += 15;

    // Grammar checks
    const trimmed = userExplanation.trim();
    const startsWithCap = trimmed.charAt(0) === trimmed.charAt(0).toUpperCase() && /[A-Z]/.test(trimmed.charAt(0));
    const endsWithPunct = trimmed.endsWith(".") || trimmed.endsWith("!");
    if (startsWithCap && endsWithPunct) englishScore += 10;

    // Professional collocations
    const hasGoodCollocation = lower.includes("this function") || lower.includes("this method")
      || lower.includes("takes two") || lower.includes("in order to") || lower.includes("responsible for");
    if (hasGoodCollocation) englishScore += 20;

    englishScore = Math.min(100, Math.max(15, englishScore));

    let englishFeedback = "";
    if (englishScore >= 85) {
      englishFeedback = "🌟 Diễn đạt tiếng Anh rất tự nhiên, chuẩn văn phong kỹ thuật (Technical English) với cấu trúc câu mạch lạc!";
    } else if (englishScore >= 65) {
      englishFeedback = "👍 Tiếng Anh của bạn dễ hiểu. Để câu văn chuyên nghiệp hơn trong môi trường doanh nghiệp, hãy tham khảo câu gợi ý bên dưới.";
    } else {
      englishFeedback = "💡 Câu văn còn hơi ngắn hoặc mang tính dịch từng từ (Word-by-word). Hãy dùng các mẫu câu chuẩn như 'This function takes... and returns...'";
    }

    return {
      technicalScore: techScore,
      englishScore: englishScore,
      technicalFeedback: techFeedback,
      englishFeedback: englishFeedback,
      nativePhrasingSample: snippet.idealExplanationEn,
      matchedTerms: matchedTerms,
      missingTerms: missingTerms
    };
  }

  /**
   * Rubric evaluation for IT Interview responses.
   */
  static evaluateInterviewResponse(question, userSpokenAnswer) {
    if (!userSpokenAnswer || !userSpokenAnswer.trim()) {
      return {
        overallScore: 0,
        grammarScore: 0,
        vocabScore: 0,
        fluencyScore: 0,
        technicalScore: 0,
        feedbackVi: "Bạn chưa trả lời câu hỏi phỏng vấn. Hãy thử lại bằng giọng nói qua micro hoặc gõ văn bản.",
        sampleAnswerEn: question.sampleAnswerEn
      };
    }

    const lower = userSpokenAnswer.toLowerCase().trim();
    const words = lower.split(/\s+/);

    let matched = 0;
    const expected = question.expectedKeywords || [];
    for (const kw of expected) {
      if (lower.includes(kw.toLowerCase())) matched++;
    }

    const technicalScore = Math.min(100, 30 + Math.round((expected.length ? matched / expected.length : 1) * 70));
    const vocabScore = Math.min(100, 40 + (matched * 12));
    const fluencyScore = Math.min(100, Math.max(30, words.length * 2));
    const grammarScore = Math.min(100, 60 + (words.length >= 15 ? 25 : 10));

    const overallScore = Math.round((technicalScore * 0.4) + (vocabScore * 0.2) + (fluencyScore * 0.2) + (grammarScore * 0.2));

    let feedback = "";
    if (overallScore >= 80) {
      feedback = "🌟 Phản xạ tuyệt vời! Bạn trả lời gãy gọn, đúng trọng tâm kỹ thuật và sử dụng từ ngữ chuyên ngành thuyết phục.\n";
    } else if (overallScore >= 60) {
      feedback = "👍 Câu trả lời khá tốt. Hãy nói chi tiết hơn và chêm thêm các từ khóa chuyên ngành để tạo ấn tượng mạnh với nhà tuyển dụng.\n";
    } else {
      feedback = "💡 Cần mở rộng câu trả lời. Tránh trả lời quá ngắn dạng 'Yes/No'. Hãy đưa ví dụ dự án thực tế bạn từng làm.\n";
    }
    feedback += `• Số từ vựng kỹ thuật chạm đúng: ${matched}/${expected.length}`;

    return {
      overallScore,
      grammarScore,
      vocabScore,
      fluencyScore,
      technicalScore,
      feedbackVi: feedback,
      sampleAnswerEn: question.sampleAnswerEn
    };
  }

  /**
   * Bug Report English Trainer
   */
  static evaluateBugReport(title, steps, expected, actual, environment) {
    let score = 40;
    const suggestions = [];

    // 1. Title
    if (title && title.trim().length >= 10) {
      score += 15;
      const lower = title.toLowerCase();
      if (lower.startsWith("[bug]") || lower.includes("when") || lower.includes("fails")) {
        score += 5;
      }
    } else {
      suggestions.push("Tiêu đề Bug Title nên ngắn gọn nhưng đầy đủ bối cảnh (ví dụ: '[Auth] Login button fails to trigger request on iOS 16')");
    }

    // 2. Steps
    if (steps && (steps.includes("1.") || steps.includes("1-") || steps.toLowerCase().includes("step"))) {
      score += 15;
    } else {
      suggestions.push("Các bước tái hiện (Steps to Reproduce) nên đánh số thứ tự rõ ràng (1, 2, 3...)");
    }

    // 3. Expected & Actual
    if (expected && expected.trim().length >= 8 && actual && actual.trim().length >= 8) {
      score += 15;
    } else {
      suggestions.push("Cần phân tách rõ ràng kết quả mong đợi (Expected Result) và kết quả thực tế lỗi (Actual Result).");
    }

    // 4. Environment
    if (environment && environment.trim().length >= 5) {
      score += 10;
    } else {
      suggestions.push("Đừng quên cung cấp thông tin môi trường lỗi (Environment: OS version, Browser, App build version).");
    }

    score = Math.min(100, score);
    return {
      score,
      suggestions,
      isPass: score >= 70
    };
  }

  /**
   * Conventional Commits Validator
   */
  static evaluateGitCommit(commitMessage) {
    if (!commitMessage || !commitMessage.trim()) {
      return {
        score: 0,
        feedbackVi: "Chưa nhập nội dung Commit Message.",
        tips: ["Hãy nhập commit message để AI kiểm tra."]
      };
    }

    const msg = commitMessage.trim();
    let score = 50;
    const tips = [];

    // Conventional Commits check: feat, fix, docs, refactor, test, chore, perf, ci
    const hasConventionalType = /^(feat|fix|docs|refactor|test|chore|perf|ci)(\([a-zA-Z0-9_-]+\))?: .+/.test(msg);
    if (hasConventionalType) {
      score += 30;
    } else {
      tips.push("Nên tuân thủ chuẩn Conventional Commits (ví dụ: 'feat: add user authentication API' hoặc 'fix(camera): resolve memory leak')");
    }

    // Imperative mood check (avoid past tense: added, fixed, updated)
    const lower = msg.toLowerCase();
    if (lower.includes("fixed") || lower.includes("added") || lower.includes("updating")) {
      tips.push("Tránh dùng thì quá khứ (added, fixed) hoặc V-ing; hãy dùng động từ nguyên mẫu thể mệnh lệnh (add, fix, implement, refactor).");
    } else {
      score += 10;
    }

    // Length check (between 12 and 72 chars)
    if (msg.length <= 72 && msg.length >= 12) {
      score += 10;
    }

    score = Math.min(100, score);
    return {
      score,
      tips,
      isStandard: score >= 80
    };
  }

  /**
   * Daily Standup Scrum Evaluator
   */
  static evaluateDailyStandup(yesterday, today, blockers) {
    let score = 40;
    const remarks = [];

    if (yesterday && yesterday.trim().length >= 10) {
      score += 20;
    } else {
      remarks.push("Phần 'Hôm qua đã làm gì' còn quá ngắn gọn.");
    }

    if (today && today.trim().length >= 10) {
      score += 20;
    } else {
      remarks.push("Phần 'Hôm nay sẽ làm gì' cần nêu rõ đầu việc cụ thể.");
    }

    if (blockers && blockers.trim()) {
      score += 20;
    } else {
      remarks.push("Nếu không có trở ngại, hãy nói rõ: 'No blockers at the moment' để thể hiện sự chủ động.");
    }

    score = Math.min(100, score);
    return {
      score,
      remarks,
      isPass: score >= 75
    };
  }
}
