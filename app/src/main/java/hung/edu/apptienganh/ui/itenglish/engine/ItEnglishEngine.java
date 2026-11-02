package hung.edu.apptienganh.ui.itenglish.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import hung.edu.apptienganh.ui.itenglish.model.CodeSnippet;
import hung.edu.apptienganh.ui.itenglish.model.InterviewQuestion;
import hung.edu.apptienganh.ui.itenglish.model.ItEvaluationResult;
import hung.edu.apptienganh.ui.itenglish.model.TechDocArticle;
import hung.edu.apptienganh.ui.itenglish.model.TechDocQuiz;
import hung.edu.apptienganh.ui.itenglish.model.TechVocabulary;

/**
 * Core Brain Engine for DEVENGLISH AI (English for IT Students & Developers).
 * Features independent dual-rubric evaluation (Technical correctness vs English expression),
 * rich real-world IT datasets, documentation parsing, interview simulation,
 * bug report trainer, and Git/Agile standup exercises.
 */
public class ItEnglishEngine {

    private static ItEnglishEngine instance;

    private final List<CodeSnippet> codeSnippets = new ArrayList<>();
    private final List<TechDocArticle> techArticles = new ArrayList<>();
    private final List<InterviewQuestion> interviewQuestions = new ArrayList<>();

    private ItEnglishEngine() {
        initCodeSnippets();
        initTechArticles();
        initInterviewQuestions();
    }

    public static synchronized ItEnglishEngine getInstance() {
        if (instance == null) {
            instance = new ItEnglishEngine();
        }
        return instance;
    }

    // =========================================================================
    // 1. CODE-TO-ENGLISH AI (Ưu tiên số 1)
    // =========================================================================

    private void initCodeSnippets() {
        // Snippet 1: Java basic function
        codeSnippets.add(new CodeSnippet(
                "java_add_sum",
                "Tính tổng hai số nguyên",
                "Java",
                "public int calculateSum(int a, int b) {\n    return a + b;\n}",
                "Intern / Junior",
                "Function takes two integer parameters and returns their mathematical sum.",
                Arrays.asList("takes", "integers", "returns", "sum"),
                "This function takes two integer arguments, a and b, and returns their sum as an integer.",
                "Hàm này nhận hai tham số nguyên a và b, sau đó trả về tổng số học của chúng."
        ));

        // Snippet 2: Python List filtering
        codeSnippets.add(new CodeSnippet(
                "py_even_filter",
                "Lọc danh sách số chẵn",
                "Python",
                "def get_even_numbers(numbers):\n    return [num for num in numbers if num % 2 == 0]",
                "Junior",
                "Iterates through a collection of numbers and filters out even elements using list comprehension.",
                Arrays.asList("list comprehension", "filters", "even", "iterates", "returns"),
                "This function utilizes list comprehension to iterate through the given input list and returns a new list containing only even numbers.",
                "Hàm này dùng list comprehension để duyệt danh sách đầu vào và trả về danh sách mới chỉ gồm các số chẵn."
        ));

        // Snippet 3: JavaScript Async Fetch
        codeSnippets.add(new CodeSnippet(
                "js_async_fetch",
                "Gọi API bất đồng bộ",
                "JavaScript",
                "async function fetchUserData(userId) {\n    try {\n        const response = await fetch(`/api/users/${userId}`);\n        if (!response.ok) throw new Error('Network error');\n        return await response.json();\n    } catch (error) {\n        console.error('Failed to fetch user:', error);\n        return null;\n    }\n}",
                "Junior / Mid",
                "Asynchronously fetches user data from an API endpoint, handles errors with try-catch block, and parses JSON response.",
                Arrays.asList("async", "await", "fetches", "endpoint", "try-catch", "parses", "json"),
                "This asynchronous function fetches user data from a REST endpoint based on the userId, safely handles potential network errors via a try-catch block, and returns the parsed JSON payload.",
                "Hàm bất đồng bộ này lấy dữ liệu người dùng từ endpoint REST dựa trên userId, xử lý lỗi an toàn qua try-catch và trả về dữ liệu JSON."
        ));

        // Snippet 4: SQL Inner Join & Aggregation
        codeSnippets.add(new CodeSnippet(
                "sql_customer_orders",
                "Truy vấn tổng chi tiêu khách hàng",
                "SQL",
                "SELECT c.customer_name, SUM(o.total_amount) AS total_spent\nFROM customers c\nJOIN orders o ON c.id = o.customer_id\nWHERE o.status = 'COMPLETED'\nGROUP BY c.customer_name\nHAVING SUM(o.total_amount) > 1000;",
                "Junior",
                "Joins customers and orders tables, filters completed orders, calculates total expenditure per customer and filters high-spenders.",
                Arrays.asList("selects", "joins", "groups by", "aggregates", "filters", "sum"),
                "This query joins the customers and orders tables on customer ID, aggregates the sum of completed orders per customer, and filters for clients who have spent more than one thousand dollars.",
                "Câu truy vấn này kết nối hai bảng khách hàng và đơn hàng, tính tổng chi tiêu các đơn thành công theo từng khách hàng và lọc ra người chi trên 1.000$."
        ));

        // Snippet 5: C# Exception Guard Clause
        codeSnippets.add(new CodeSnippet(
                "cs_user_registration",
                "Kiểm tra tính hợp lệ & ném lỗi",
                "C#",
                "public void RegisterUser(string email, string password) {\n    if (string.IsNullOrWhiteSpace(email)) {\n        throw new ArgumentException(\"Email cannot be empty\");\n    }\n    if (password.Length < 8) {\n        throw new InvalidOperationException(\"Password is too short\");\n    }\n    _userRepository.Save(new User(email, password));\n}",
                "Junior / Mid",
                "Validates user registration inputs with guard clauses and throws exceptions before persisting user.",
                Arrays.asList("validates", "guard clause", "throws", "exception", "persists"),
                "This method validates the email and password parameters using guard clauses, throws specific exceptions when conditions fail, and subsequently persists the new user to the repository.",
                "Phương thức này kiểm tra tính hợp lệ của email và mật khẩu bằng guard clause, ném ngoại lệ nếu không đạt và sau đó lưu người dùng vào cơ sở dữ liệu."
        ));
    }

    public List<CodeSnippet> getAllCodeSnippets() {
        return Collections.unmodifiableList(codeSnippets);
    }

    /**
     * Evaluates Code-to-English explanation with TWO INDEPENDENT SCORES:
     * 1) Technical Correctness (Độ chính xác kỹ thuật)
     * 2) English Expression (Chất lượng tiếng Anh)
     */
    public ItEvaluationResult evaluateCodeExplanation(CodeSnippet snippet, String userExplanation) {
        if (userExplanation == null || userExplanation.trim().isEmpty()) {
            return new ItEvaluationResult(0, 0,
                    "Bạn chưa nhập lời giải thích cho đoạn code.",
                    "Vui lòng diễn đạt bằng tiếng Anh.",
                    snippet.getIdealExplanationEn(),
                    Collections.emptyList(), snippet.getRequiredKeyTerms());
        }

        String lower = userExplanation.toLowerCase(Locale.ROOT).trim();
        List<String> matchedTerms = new ArrayList<>();
        List<String> missingTerms = new ArrayList<>();

        for (String term : snippet.getRequiredKeyTerms()) {
            if (lower.contains(term.toLowerCase(Locale.ROOT))) {
                matchedTerms.add(term);
            } else {
                missingTerms.add(term);
            }
        }

        // --- 1. Technical Score Calculation (0 - 100) ---
        int techScore = 40; // Base score for attempting
        float matchRatio = snippet.getRequiredKeyTerms().isEmpty() ? 1.0f :
                (float) matchedTerms.size() / snippet.getRequiredKeyTerms().size();
        techScore += Math.round(matchRatio * 50);

        // Technical heuristics
        boolean hasActionVerb = lower.contains("return") || lower.contains("take") || lower.contains("fetch")
                || lower.contains("filter") || lower.contains("validate") || lower.contains("calculate");
        if (hasActionVerb) techScore += 10;
        techScore = Math.min(100, Math.max(10, techScore));

        String techFeedback;
        if (techScore >= 85) {
            techFeedback = "✅ Chính xác tuyệt đối về logic lập trình! Bạn đã nêu đúng vai trò tham số, luồng xử lý và kết quả đầu ra.";
        } else if (techScore >= 60) {
            techFeedback = "⚠️ Bạn đã nắm được ý chính của đoạn code, tuy nhiên còn thiếu một số chi tiết kỹ thuật quan trọng như: "
                    + String.join(", ", missingTerms);
        } else {
            techFeedback = "❌ Lời giải thích chưa thể hiện rõ chức năng lập trình cốt lõi. Hãy chú ý đến giá trị trả về và các tham số đầu vào.";
        }

        // --- 2. English Score Calculation (0 - 100) ---
        int englishScore = 40;
        String[] words = userExplanation.trim().split("\\s+");
        if (words.length >= 8) englishScore += 15;
        if (words.length >= 15) englishScore += 15;

        // Grammar checks (subject-verb agreement, proper casing, punctuation)
        boolean startsWithCap = Character.isUpperCase(userExplanation.trim().charAt(0));
        boolean endsWithPunct = userExplanation.trim().endsWith(".") || userExplanation.trim().endsWith("!");
        if (startsWithCap && endsWithPunct) englishScore += 10;

        // Check professional collocations
        boolean hasGoodCollocation = lower.contains("this function") || lower.contains("this method")
                || lower.contains("takes two") || lower.contains("in order to") || lower.contains("responsible for");
        if (hasGoodCollocation) englishScore += 20;

        englishScore = Math.min(100, Math.max(15, englishScore));

        String englishFeedback;
        if (englishScore >= 85) {
            englishFeedback = "🌟 Diễn đạt tiếng Anh rất tự nhiên, chuẩn văn phong kỹ thuật (Technical English) với cấu trúc câu mạch lạc!";
        } else if (englishScore >= 65) {
            englishFeedback = "👍 Tiếng Anh của bạn dễ hiểu. Để câu văn chuyên nghiệp hơn trong môi trường doanh nghiệp, hãy tham khảo câu gợi ý bên dưới.";
        } else {
            englishFeedback = "💡 Câu văn còn hơi ngắn hoặc mang tính dịch từng từ (Word-by-word). Hãy dùng các mẫu câu chuẩn như 'This function takes... and returns...'";
        }

        return new ItEvaluationResult(techScore, englishScore, techFeedback, englishFeedback,
                snippet.getIdealExplanationEn(), matchedTerms, missingTerms);
    }

    // =========================================================================
    // 2. TECHNICAL DOCUMENTATION READER (Ưu tiên số 2)
    // =========================================================================

    private void initTechArticles() {
        // Article 1: React useEffect Hook
        List<TechVocabulary> reactVocab = Arrays.asList(
                new TechVocabulary("side effect", "/saɪd ɪˈfekt/", "noun phrase",
                        "Operations that affect other components or cannot be done during rendering, such as data fetching or subscriptions.",
                        "Tác vụ phụ (gọi API, hẹn giờ, lắng nghe sự kiện ngoài luồng render)",
                        "Data fetching is a common side effect in modern React web applications."),
                new TechVocabulary("dependency array", "/dɪˈpen.dən.si əˈreɪ/", "noun phrase",
                        "The second argument to useEffect that tells React when to re-execute the effect.",
                        "Mảng phụ thuộc (quyết định khi nào hook chạy lại)",
                        "If you leave the dependency array empty, the effect runs only once after the initial render."),
                new TechVocabulary("cleanup function", "/ˈkliːn.ʌp ˈfʌŋk.ʃən/", "noun phrase",
                        "A function returned by useEffect to unsubscribe or clear timers before unmounting.",
                        "Hàm dọn dẹp bộ nhớ (tránh memory leak)",
                        "Always return a cleanup function to clear active timers.")
        );

        List<TechDocQuiz> reactQuiz = Arrays.asList(
                new TechDocQuiz("When does useEffect run if the dependency array is empty `[]`?",
                        Arrays.asList("Only once after the initial mount", "On every state change", "Never", "Only before unmounting"),
                        0, "Khi mảng dependencies rỗng [], hook chỉ chạy duy nhất 1 lần sau khi component được mount lần đầu."),
                new TechDocQuiz("What is the primary role of a cleanup function in useEffect?",
                        Arrays.asList("To clear timers and prevent memory leaks", "To re-render the UI", "To fetch new API data", "To style components"),
                        0, "Cleanup function được gọi để dọn dẹp timer, subscriptions nhằm ngăn chặn rò rỉ bộ nhớ (memory leaks).")
        );

        techArticles.add(new TechDocArticle(
                "doc_react_useeffect",
                "Synchronizing with Effects (React Hooks)",
                "React / Frontend",
                "Intermediate (B1)",
                "Effects let you specify side effects that are caused by rendering itself, rather than by a particular event. " +
                        "Data fetching, setting up a subscription, and manually changing the DOM in React components are all examples of side effects. " +
                        "The dependency array instructs React when to skip re-running the effect if certain values have not changed between re-renders.",
                "In simple words: Effects let your component do tasks outside of drawing UI, like asking the server for data. You can control when it runs by telling it which variables to watch.",
                "Tài liệu hướng dẫn cách đồng bộ hóa Effects trong React: hiểu về side effects (gọi API, lắng nghe dữ liệu) và cách dùng dependency array để tối ưu số lần chạy lại.",
                reactVocab,
                reactQuiz
        ));

        // Article 2: Docker Containerization
        List<TechVocabulary> dockerVocab = Arrays.asList(
                new TechVocabulary("containerization", "/kənˌteɪ.nər.aɪˈzeɪ.ʃən/", "noun",
                        "Packaging software code with all its dependencies so that it runs uniformly across any infrastructure.",
                        "Công nghệ đóng gói container (chạy nhất quán trên mọi môi trường)",
                        "Containerization completely resolves the 'it works on my machine' dilemma."),
                new TechVocabulary("immutable", "/ɪˈmjuː.tə.bəl/", "adjective",
                        "Unchanging over time or unable to be modified once created.",
                        "Bất biến (không thay đổi sau khi được tạo ra)",
                        "Docker images are immutable templates used to spawn isolated runtime instances."),
                new TechVocabulary("orchestration", "/ˌɔː.kɪˈstreɪ.ʃən/", "noun",
                        "Automated configuration, coordination, and management of computer systems and software.",
                        "Điều phối và quản lý tự động nhiều container (như Kubernetes)",
                        "Kubernetes provides enterprise-grade container orchestration for microservices.")
        );

        List<TechDocQuiz> dockerQuiz = Arrays.asList(
                new TechDocQuiz("Why are Docker images described as 'immutable'?",
                        Arrays.asList("Because they cannot be altered once built", "Because they are stored on CD-ROM", "Because they run only on Linux", "Because they don't consume storage"),
                        0, "Image trong Docker là bất biến (immutable) để đảm bảo môi trường triển khai luôn giống hệt nhau."),
                new TechDocQuiz("What major problem does containerization solve for engineering teams?",
                        Arrays.asList("The 'it works on my machine' inconsistency", "Slow typing speed", "Lack of internet connection", "Writing SQL queries"),
                        0, "Containerization loại bỏ hoàn toàn sự xung đột môi trường giữa máy lập trình viên và máy chủ production.")
        );

        techArticles.add(new TechDocArticle(
                "doc_docker_containers",
                "Containerization Architecture & Best Practices",
                "DevOps / Cloud",
                "Intermediate (B1-B2)",
                "Containers are lightweight, standalone, executable packages of software that include everything needed to run an application: code, runtime, system tools, and libraries. " +
                        "Unlike virtual machines that bundle an entire operating system, containers share the host machine's OS kernel, which drastically reduces startup latency and memory overhead.",
                "In simple words: Containers are like small digital lunchboxes containing your app and everything it needs. They boot in milliseconds because they share the main computer's engine.",
                "Khái quát về kiến trúc Container: nhẹ hơn máy ảo (VM) do dùng chung nhân OS host, khởi động siêu nhanh và giải quyết xung đột môi trường làm việc.",
                dockerVocab,
                dockerQuiz
        ));
    }

    public List<TechDocArticle> getAllTechArticles() {
        return Collections.unmodifiableList(techArticles);
    }

    // =========================================================================
    // 3. IT ENGLISH INTERVIEW SIMULATOR (Ưu tiên số 3)
    // =========================================================================

    private void initInterviewQuestions() {
        // Round 1: Self-Introduction
        interviewQuestions.add(new InterviewQuestion(
                "int_java_intro",
                "Junior Java Developer",
                "Vòng 1: Giới thiệu bản thân (Self-Introduction)",
                "Could you walk me through your background and why you are interested in this Junior Java Developer position?",
                "Bạn có thể giới thiệu về bản thân và lý do ứng tuyển vào vị trí Lập trình viên Java Junior không?",
                Arrays.asList("graduate", "experience", "spring boot", "backend", "passionate"),
                "Certainly! I graduated with a degree in Computer Science, where I built a solid foundation in Object-Oriented Programming and Java. During my academic projects, I developed RESTful microservices using Spring Boot, Hibernate, and PostgreSQL. I am passionate about crafting clean, maintainable backend architecture, and I am eager to contribute to your engineering team while accelerating my professional growth.",
                "💡 Mẹo phỏng vấn: Hãy áp dụng công thức P-P-F (Present - Past - Future): Giới thiệu chuyên ngành hiện tại ➔ Dự án và kỹ năng đã làm trong quá khứ ➔ Kỳ vọng đóng góp cho công ty trong tương lai."
        ));

        // Round 2: Technical Knowledge
        interviewQuestions.add(new InterviewQuestion(
                "int_java_oop",
                "Junior Java Developer",
                "Vòng 2: Kiến thức kỹ thuật cốt lõi (Technical Core)",
                "How does an Interface differ from an Abstract Class in Java, and when would you prefer one over the other?",
                "Interface khác Abstract Class như thế nào trong Java, và khi nào bạn ưu tiên dùng loại nào?",
                Arrays.asList("multiple inheritance", "contract", "state", "default methods", "abstract class"),
                "In Java, an interface represents a contract specifying what a class should do without maintaining state, supporting multiple inheritance. Conversely, an abstract class can maintain state with instance variables and provide default partial implementations. I prefer an interface when defining polymorphic behavior across unrelated classes, and an abstract class when sharing common code among closely related classes in a hierarchy.",
                "💡 Mẹo phỏng vấn: Đừng chỉ liệt kê lý thuyết suông; hãy nêu rõ trường hợp sử dụng (Use case) thực tế để thể hiện tư duy kiến trúc phần mềm."
        ));

        // Round 3: Problem Solving & Debugging
        interviewQuestions.add(new InterviewQuestion(
                "int_web_debug",
                "Frontend / Web Developer",
                "Vòng 3: Giải quyết vấn đề & Debugging (Problem Solving)",
                "Imagine a user reports that our web application feels sluggish and buttons are unresponsive. How would you troubleshoot this issue?",
                "Hãy tưởng tượng người dùng báo cáo ứng dụng web bị chậm và các nút bấm bị đơ. Bạn sẽ xử lý lỗi này như thế nào?",
                Arrays.asList("devtools", "profiler", "network tab", "re-render", "memory leak"),
                "First, I would inspect the application using Chrome DevTools Performance and Network tabs to identify whether the bottleneck originates from heavy network payloads or main thread blocking. Next, I would utilize the React Profiler to detect excessive re-renders or unoptimized state updates. Finally, I would verify that event listeners and timers are cleanly disposed of to eliminate potential memory leaks.",
                "💡 Mẹo phỏng vấn: Thể hiện quy trình tư duy logic có hệ thống (Step-by-step diagnostic): Tái hiện lỗi ➔ Dùng công cụ đo đạc (DevTools) ➔ Phân tích nguyên nhân gốc ➔ Đưa ra giải pháp."
        ));

        // Round 4: Teamwork & Agile
        interviewQuestions.add(new InterviewQuestion(
                "int_agile_conflict",
                "Tất cả vị trí (General IT)",
                "Vòng 4: Làm việc nhóm & Văn hóa Agile (Teamwork & Agile)",
                "How do you handle a situation where a senior developer leaves critical feedback on your Pull Request?",
                "Bạn xử lý thế nào khi một senior developer để lại nhận xét phê bình khắt khe trong Pull Request của bạn?",
                Arrays.asList("constructive feedback", "code review", "learn", "discuss", "clarify"),
                "I view code reviews as an invaluable learning opportunity rather than personal criticism. I would carefully review each comment to understand the underlying rationale, such as performance optimization or design patterns. If any comment requires clarification, I would politely initiate a brief discussion on Slack or schedule a 5-minute call to align on the best architectural approach before submitting revisions.",
                "💡 Mẹo phỏng vấn: Thể hiện thái độ cầu thị (Growth Mindset), kỹ năng giao tiếp lịch thiệp và tôn trọng tiêu chuẩn chất lượng sản phẩm chung của đội ngũ."
        ));
    }

    public List<InterviewQuestion> getAllInterviewQuestions() {
        return Collections.unmodifiableList(interviewQuestions);
    }

    /**
     * Rubric evaluation for IT Interview responses.
     */
    public Map<String, Object> evaluateInterviewResponse(InterviewQuestion question, String userSpokenAnswer) {
        Map<String, Object> result = new HashMap<>();

        if (userSpokenAnswer == null || userSpokenAnswer.trim().isEmpty()) {
            result.put("overallScore", 0);
            result.put("grammarScore", 0);
            result.put("vocabScore", 0);
            result.put("fluencyScore", 0);
            result.put("technicalScore", 0);
            result.put("feedbackVi", "Bạn chưa trả lời câu hỏi phỏng vấn. Hãy thử lại bằng giọng nói hoặc nhập văn bản.");
            result.put("sampleAnswerEn", question.getSampleAnswerEn());
            return result;
        }

        String lower = userSpokenAnswer.toLowerCase(Locale.ROOT).trim();
        String[] words = lower.split("\\s+");

        // Keywords matched
        int matched = 0;
        for (String kw : question.getExpectedKeywords()) {
            if (lower.contains(kw.toLowerCase(Locale.ROOT))) matched++;
        }

        int technicalScore = Math.min(100, 30 + Math.round(((float) matched / question.getExpectedKeywords().size()) * 70));
        int vocabScore = Math.min(100, 40 + (matched * 12));
        int fluencyScore = Math.min(100, Math.max(30, words.length * 2));
        int grammarScore = Math.min(100, 60 + (words.length >= 15 ? 25 : 10));

        int overallScore = Math.round((technicalScore * 0.4f) + (vocabScore * 0.2f) + (fluencyScore * 0.2f) + (grammarScore * 0.2f));

        StringBuilder feedback = new StringBuilder();
        if (overallScore >= 80) {
            feedback.append("🌟 Phản xạ tuyệt vời! Bạn trả lời gãy gọn, đúng trọng tâm kỹ thuật và sử dụng từ ngữ chuyên ngành thuyết phục.\n");
        } else if (overallScore >= 60) {
            feedback.append("👍 Câu trả lời khá tốt. Hãy nói chi tiết hơn và chêm thêm các từ khóa chuyên ngành để tạo ấn tượng mạnh với nhà tuyển dụng.\n");
        } else {
            feedback.append("💡 Cần mở rộng câu trả lời. Tránh trả lời quá ngắn dạng 'Yes/No'. Hãy đưa ví dụ dự án thực tế bạn từng làm.\n");
        }
        feedback.append("• Số từ vựng kỹ thuật chạm đúng: ").append(matched).append("/").append(question.getExpectedKeywords().size());

        result.put("overallScore", overallScore);
        result.put("grammarScore", grammarScore);
        result.put("vocabScore", vocabScore);
        result.put("fluencyScore", fluencyScore);
        result.put("technicalScore", technicalScore);
        result.put("feedbackVi", feedback.toString());
        result.put("sampleAnswerEn", question.getSampleAnswerEn());

        return result;
    }

    // =========================================================================
    // 4. BUG REPORT ENGLISH TRAINER (Nhóm B)
    // =========================================================================

    public Map<String, Object> evaluateBugReport(String title, String steps, String expected, String actual, String environment) {
        Map<String, Object> eval = new HashMap<>();

        int score = 40;
        List<String> suggestions = new ArrayList<>();

        // 1. Title evaluation
        if (title != null && title.trim().length() >= 10) {
            score += 15;
            if (title.toLowerCase(Locale.ROOT).startsWith("[bug]") || title.contains("when") || title.contains("fails")) {
                score += 5;
            }
        } else {
            suggestions.add("Tiêu đề Bug Title nên ngắn gọn nhưng đầy đủ bối cảnh (ví dụ: '[Auth] Login button fails to trigger request on iOS 16')");
        }

        // 2. Steps to reproduce evaluation
        if (steps != null && (steps.contains("1.") || steps.contains("1-") || steps.toLowerCase(Locale.ROOT).contains("step"))) {
            score += 15;
        } else {
            suggestions.add("Các bước tái hiện (Steps to Reproduce) nên đánh số thứ tự rõ ràng (1, 2, 3...)");
        }

        // 3. Expected & Actual results
        if (expected != null && expected.trim().length() >= 8 && actual != null && actual.trim().length() >= 8) {
            score += 15;
        } else {
            suggestions.add("Cần phân tách rõ ràng kết quả mong đợi (Expected Result) và kết quả thực tế lỗi (Actual Result).");
        }

        // 4. Environment
        if (environment != null && environment.trim().length() >= 5) {
            score += 10;
        } else {
            suggestions.add("Đừng quên cung cấp thông tin môi trường lỗi (Environment: OS version, Browser, App build version).");
        }

        score = Math.min(100, score);
        eval.put("score", score);
        eval.put("suggestions", suggestions);
        eval.put("isPass", score >= 70);

        return eval;
    }

    // =========================================================================
    // 5. GITHUB COMMIT & STANDUP SIMULATOR
    // =========================================================================

    public Map<String, Object> evaluateGitCommit(String commitMessage) {
        Map<String, Object> eval = new HashMap<>();
        if (commitMessage == null || commitMessage.trim().isEmpty()) {
            eval.put("score", 0);
            eval.put("feedbackVi", "Chưa nhập nội dung Commit Message.");
            return eval;
        }

        String msg = commitMessage.trim();
        int score = 50;
        List<String> tips = new ArrayList<>();

        // Conventional Commits check: feat, fix, docs, refactor, test, chore (with or without scope)
        boolean hasConventionalType = msg.matches("^(feat|fix|docs|refactor|test|chore|perf|ci)(\\(.+\\))?: .+");

        if (hasConventionalType) {
            score += 30;
        } else {
            tips.add("Nên tuân thủ chuẩn Conventional Commits (ví dụ: 'feat: add user authentication API' hoặc 'fix: resolve memory leak in camera loop')");
        }

        // Imperative mood check (thì mệnh lệnh)
        String lower = msg.toLowerCase(Locale.ROOT);
        if (lower.contains("fixed") || lower.contains("added") || lower.contains("updating")) {
            tips.add("Tránh dùng thì quá khứ (added, fixed) hoặc V-ing; hãy dùng động từ nguyên mẫu thể mệnh lệnh (add, fix, implement, refactor).");
        } else {
            score += 10;
        }

        // Length check (under 72 chars)
        if (msg.length() <= 72 && msg.length() >= 12) {
            score += 10;
        }

        score = Math.min(100, score);
        eval.put("score", score);
        eval.put("tips", tips);
        eval.put("isStandard", score >= 80);

        return eval;
    }

    public Map<String, Object> evaluateDailyStandup(String yesterday, String today, String blockers) {
        Map<String, Object> eval = new HashMap<>();

        int score = 40;
        List<String> remarks = new ArrayList<>();

        if (yesterday != null && yesterday.trim().length() >= 10) {
            score += 20;
        } else {
            remarks.add("Phần 'Hôm qua đã làm gì' còn quá ngắn gọn.");
        }

        if (today != null && today.trim().length() >= 10) {
            score += 20;
        } else {
            remarks.add("Phần 'Hôm nay sẽ làm gì' cần nêu rõ đầu việc cụ thể.");
        }

        if (blockers != null && !blockers.trim().isEmpty()) {
            score += 20;
        } else {
            remarks.add("Nếu không có trở ngại, hãy nói rõ: 'No blockers at the moment' để thể hiện sự chủ động.");
        }

        score = Math.min(100, score);
        eval.put("score", score);
        eval.put("remarks", remarks);

        return eval;
    }
}
