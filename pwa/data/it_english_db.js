// DEVENGLISH AI IT English Knowledge Base (100% Offline)
export const CODE_SNIPPETS = [
  {
    "id": "java_add_sum",
    "title": "Tính tổng hai số nguyên",
    "language": "Java",
    "code": "public int calculateSum(int a, int b) {\n    return a + b;\n}",
    "level": "Intern / Junior",
    "technicalFunctionVi": "Hàm nhận hai tham số nguyên và trả về tổng số học của chúng.",
    "requiredKeyTerms": [
      "takes",
      "integers",
      "returns",
      "sum"
    ],
    "idealExplanationEn": "This function takes two integer arguments, a and b, and returns their sum as an integer.",
    "idealExplanationVi": "Hàm này nhận hai tham số nguyên a và b, sau đó trả về tổng số học của chúng."
  },
  {
    "id": "py_even_filter",
    "title": "Lọc danh sách số chẵn",
    "language": "Python",
    "code": "def get_even_numbers(numbers):\n    return [num for num in numbers if num % 2 == 0]",
    "level": "Junior",
    "technicalFunctionVi": "Duyệt qua danh sách số và lọc ra các phần tử chẵn bằng list comprehension.",
    "requiredKeyTerms": [
      "list comprehension",
      "filters",
      "even",
      "iterates",
      "returns"
    ],
    "idealExplanationEn": "This function utilizes list comprehension to iterate through the given input list and returns a new list containing only even numbers.",
    "idealExplanationVi": "Hàm này dùng list comprehension để duyệt danh sách đầu vào và trả về danh sách mới chỉ gồm các số chẵn."
  },
  {
    "id": "js_async_fetch",
    "title": "Gọi API bất đồng bộ",
    "language": "JavaScript",
    "code": "async function fetchUserData(userId) {\n    try {\n        const response = await fetch(`/api/users/${userId}`);\n        if (!response.ok) throw new Error('Network error');\n        return await response.json();\n    } catch (error) {\n        console.error('Failed to fetch user:', error);\n        return null;\n    }\n}",
    "level": "Junior / Mid",
    "technicalFunctionVi": "Lấy dữ liệu người dùng bất đồng bộ từ endpoint API, xử lý lỗi bằng try-catch và phân tích JSON.",
    "requiredKeyTerms": [
      "async",
      "await",
      "fetches",
      "endpoint",
      "try-catch",
      "parses",
      "json"
    ],
    "idealExplanationEn": "This asynchronous function fetches user data from a REST endpoint based on the userId, safely handles potential network errors via a try-catch block, and returns the parsed JSON payload.",
    "idealExplanationVi": "Hàm bất đồng bộ này lấy dữ liệu người dùng từ endpoint REST dựa trên userId, xử lý lỗi an toàn qua try-catch và trả về dữ liệu JSON."
  },
  {
    "id": "sql_customer_orders",
    "title": "Truy vấn tổng chi tiêu khách hàng",
    "language": "SQL",
    "code": "SELECT c.customer_name, SUM(o.total_amount) AS total_spent\nFROM customers c\nJOIN orders o ON c.id = o.customer_id\nWHERE o.status = 'COMPLETED'\nGROUP BY c.customer_name\nHAVING SUM(o.total_amount) > 1000;",
    "level": "Junior",
    "technicalFunctionVi": "Nối bảng customers và orders, gom nhóm theo khách hàng và lọc các khách chi trên $1000.",
    "requiredKeyTerms": [
      "selects",
      "joins",
      "groups by",
      "aggregates",
      "filters",
      "sum"
    ],
    "idealExplanationEn": "This query joins the customers and orders tables on customer ID, aggregates the sum of completed orders per customer, and filters for clients who have spent more than one thousand dollars.",
    "idealExplanationVi": "Câu truy vấn này kết nối hai bảng khách hàng và đơn hàng, tính tổng chi tiêu các đơn thành công theo từng khách hàng và lọc ra người chi trên 1.000$."
  },
  {
    "id": "cs_user_registration",
    "title": "Kiểm tra tính hợp lệ & ném lỗi",
    "language": "C#",
    "code": "public void RegisterUser(string email, string password) {\n    if (string.IsNullOrWhiteSpace(email)) {\n        throw new ArgumentException(\"Email cannot be empty\");\n    }\n    if (password.Length < 8) {\n        throw new InvalidOperationException(\"Password is too short\");\n    }\n    _userRepository.Save(new User(email, password));\n}",
    "level": "Junior / Mid",
    "technicalFunctionVi": "Xác thực email và mật khẩu bằng guard clause trước khi lưu người dùng vào repository.",
    "requiredKeyTerms": [
      "validates",
      "guard clause",
      "throws",
      "exception",
      "persists"
    ],
    "idealExplanationEn": "This method validates the email and password parameters using guard clauses, throws specific exceptions when conditions fail, and subsequently persists the new user to the repository.",
    "idealExplanationVi": "Phương thức này kiểm tra tính hợp lệ của email và mật khẩu bằng guard clause, ném ngoại lệ nếu không đạt và sau đó lưu người dùng vào cơ sở dữ liệu."
  }
];
export const TECH_ARTICLES = [
  {
    "id": "doc_react_useeffect",
    "title": "Synchronizing with Effects (React Hooks)",
    "domain": "React / Frontend",
    "cefrLevel": "Intermediate (B1)",
    "originalText": "Effects let you specify side effects that are caused by rendering itself, rather than by a particular event. Data fetching, setting up a subscription, and manually changing the DOM in React components are all examples of side effects. The dependency array instructs React when to skip re-running the effect if certain values have not changed between re-renders.",
    "simplifiedPlainEnglish": "In simple words: Effects let your component do tasks outside of drawing UI, like asking the server for data. You can control when it runs by telling it which variables to watch.",
    "overviewVi": "Tài liệu hướng dẫn cách đồng bộ hóa Effects trong React: hiểu về side effects (gọi API, lắng nghe dữ liệu) và cách dùng dependency array để tối ưu số lần chạy lại.",
    "vocabularies": [
      {
        "term": "side effect",
        "ipa": "/saɪd ɪˈfekt/",
        "partOfSpeech": "noun phrase",
        "definitionEn": "Operations that affect other components or cannot be done during rendering, such as data fetching or subscriptions.",
        "definitionVi": "Tác vụ phụ (gọi API, hẹn giờ, lắng nghe sự kiện ngoài luồng render)",
        "example": "Data fetching is a common side effect in modern React web applications."
      },
      {
        "term": "dependency array",
        "ipa": "/dɪˈpen.dən.si əˈreɪ/",
        "partOfSpeech": "noun phrase",
        "definitionEn": "The second argument to useEffect that tells React when to re-execute the effect.",
        "definitionVi": "Mảng phụ thuộc (quyết định khi nào hook chạy lại)",
        "example": "If you leave the dependency array empty, the effect runs only once after the initial render."
      },
      {
        "term": "cleanup function",
        "ipa": "/ˈkliːn.ʌp ˈfʌŋk.ʃən/",
        "partOfSpeech": "noun phrase",
        "definitionEn": "A function returned by useEffect to unsubscribe or clear timers before unmounting.",
        "definitionVi": "Hàm dọn dẹp bộ nhớ (tránh memory leak)",
        "example": "Always return a cleanup function to clear active timers."
      }
    ],
    "quizzes": [
      {
        "question": "When does useEffect run if the dependency array is empty `[]`?",
        "options": [
          "Only once after the initial mount",
          "On every state change",
          "Never",
          "Only before unmounting"
        ],
        "correctIndex": 0,
        "explanationVi": "Khi mảng dependencies rỗng [], hook chỉ chạy duy nhất 1 lần sau khi component được mount lần đầu."
      },
      {
        "question": "What is the primary role of a cleanup function in useEffect?",
        "options": [
          "To clear timers and prevent memory leaks",
          "To re-render the UI",
          "To fetch new API data",
          "To style components"
        ],
        "correctIndex": 0,
        "explanationVi": "Cleanup function được gọi để dọn dẹp timer, subscriptions nhằm ngăn chặn rò rỉ bộ nhớ (memory leaks)."
      }
    ]
  },
  {
    "id": "doc_docker_containers",
    "title": "Containerization Architecture & Best Practices",
    "domain": "DevOps / Cloud",
    "cefrLevel": "Intermediate (B1-B2)",
    "originalText": "Containers are lightweight, standalone, executable packages of software that include everything needed to run an application: code, runtime, system tools, and libraries. Unlike virtual machines that bundle an entire operating system, containers share the host machine's OS kernel, which drastically reduces startup latency and memory overhead.",
    "simplifiedPlainEnglish": "In simple words: Containers are like small digital lunchboxes containing your app and everything it needs. They boot in milliseconds because they share the main computer's engine.",
    "overviewVi": "Khái quát về kiến trúc Container: nhẹ hơn máy ảo (VM) do dùng chung nhân OS host, khởi động siêu nhanh và giải quyết xung đột môi trường làm việc.",
    "vocabularies": [
      {
        "term": "containerization",
        "ipa": "/kənˌteɪ.nər.aɪˈzeɪ.ʃən/",
        "partOfSpeech": "noun",
        "definitionEn": "Packaging software code with all its dependencies so that it runs uniformly across any infrastructure.",
        "definitionVi": "Công nghệ đóng gói container (chạy nhất quán trên mọi môi trường)",
        "example": "Containerization completely resolves the 'it works on my machine' dilemma."
      },
      {
        "term": "immutable",
        "ipa": "/ɪˈmjuː.tə.bəl/",
        "partOfSpeech": "adjective",
        "definitionEn": "Unchanging over time or unable to be modified once created.",
        "definitionVi": "Bất biến (không thay đổi sau khi được tạo ra)",
        "example": "Docker images are immutable templates used to spawn isolated runtime instances."
      },
      {
        "term": "orchestration",
        "ipa": "/ˌɔː.kɪˈstreɪ.ʃən/",
        "partOfSpeech": "noun",
        "definitionEn": "Automated configuration, coordination, and management of computer systems and software.",
        "definitionVi": "Điều phối và quản lý tự động nhiều container (như Kubernetes)",
        "example": "Kubernetes provides enterprise-grade container orchestration for microservices."
      }
    ],
    "quizzes": [
      {
        "question": "Why are Docker images described as 'immutable'?",
        "options": [
          "Because they cannot be altered once built",
          "Because they are stored on CD-ROM",
          "Because they run only on Linux",
          "Because they don't consume storage"
        ],
        "correctIndex": 0,
        "explanationVi": "Image trong Docker là bất biến (immutable) để đảm bảo môi trường triển khai luôn giống hệt nhau."
      },
      {
        "question": "What major problem does containerization solve for engineering teams?",
        "options": [
          "The 'it works on my machine' inconsistency",
          "Slow typing speed",
          "Lack of internet connection",
          "Writing SQL queries"
        ],
        "correctIndex": 0,
        "explanationVi": "Containerization loại bỏ hoàn toàn sự xung đột môi trường giữa máy lập trình viên và máy chủ production."
      }
    ]
  }
];
export const INTERVIEW_QUESTIONS = [
  {
    "id": "int_java_intro",
    "role": "Junior Java Developer",
    "round": "Vòng 1: Giới thiệu bản thân (Self-Introduction)",
    "questionEn": "Could you walk me through your background and why you are interested in this Junior Java Developer position?",
    "questionVi": "Bạn có thể giới thiệu về bản thân và lý do ứng tuyển vào vị trí Lập trình viên Java Junior không?",
    "expectedKeywords": [
      "graduate",
      "experience",
      "spring boot",
      "backend",
      "passionate"
    ],
    "sampleAnswerEn": "Certainly! I graduated with a degree in Computer Science, where I built a solid foundation in Object-Oriented Programming and Java. During my academic projects, I developed RESTful microservices using Spring Boot, Hibernate, and PostgreSQL. I am passionate about crafting clean, maintainable backend architecture, and I am eager to contribute to your engineering team while accelerating my professional growth.",
    "tipsVi": "💡 Mẹo phỏng vấn: Hãy áp dụng công thức P-P-F (Present - Past - Future): Giới thiệu chuyên ngành hiện tại ➔ Dự án và kỹ năng đã làm trong quá khứ ➔ Kỳ vọng đóng góp cho công ty trong tương lai."
  },
  {
    "id": "int_java_oop",
    "role": "Junior Java Developer",
    "round": "Vòng 2: Kiến thức kỹ thuật cốt lõi (Technical Core)",
    "questionEn": "How does an Interface differ from an Abstract Class in Java, and when would you prefer one over the other?",
    "questionVi": "Interface khác Abstract Class như thế nào trong Java, và khi nào bạn ưu tiên dùng loại nào?",
    "expectedKeywords": [
      "multiple inheritance",
      "contract",
      "state",
      "default methods",
      "abstract class"
    ],
    "sampleAnswerEn": "In Java, an interface represents a contract specifying what a class should do without maintaining state, supporting multiple inheritance. Conversely, an abstract class can maintain state with instance variables and provide default partial implementations. I prefer an interface when defining polymorphic behavior across unrelated classes, and an abstract class when sharing common code among closely related classes in a hierarchy.",
    "tipsVi": "💡 Mẹo phỏng vấn: Đừng chỉ liệt kê lý thuyết suông; hãy nêu rõ trường hợp sử dụng (Use case) thực tế để thể hiện tư duy kiến trúc phần mềm."
  },
  {
    "id": "int_web_debug",
    "role": "Frontend / Web Developer",
    "round": "Vòng 3: Giải quyết vấn đề & Debugging (Problem Solving)",
    "questionEn": "Imagine a user reports that our web application feels sluggish and buttons are unresponsive. How would you troubleshoot this issue?",
    "questionVi": "Hãy tưởng tượng người dùng báo cáo ứng dụng web bị chậm và các nút bấm bị đơ. Bạn sẽ xử lý lỗi này như thế nào?",
    "expectedKeywords": [
      "devtools",
      "profiler",
      "network tab",
      "re-render",
      "memory leak"
    ],
    "sampleAnswerEn": "First, I would inspect the application using Chrome DevTools Performance and Network tabs to identify whether the bottleneck originates from heavy network payloads or main thread blocking. Next, I would utilize the React Profiler to detect excessive re-renders or unoptimized state updates. Finally, I would verify that event listeners and timers are cleanly disposed of to eliminate potential memory leaks.",
    "tipsVi": "💡 Mẹo phỏng vấn: Thể hiện quy trình tư duy logic có hệ thống (Step-by-step diagnostic): Tái hiện lỗi ➔ Dùng công cụ đo đạc (DevTools) ➔ Phân tích nguyên nhân gốc ➔ Đưa ra giải pháp."
  },
  {
    "id": "int_agile_conflict",
    "role": "Tất cả vị trí (General IT)",
    "round": "Vòng 4: Làm việc nhóm & Văn hóa Agile (Teamwork & Agile)",
    "questionEn": "How do you handle a situation where a senior developer leaves critical feedback on your Pull Request?",
    "questionVi": "Bạn xử lý thế nào khi một senior developer để lại nhận xét phê bình khắt khe trong Pull Request của bạn?",
    "expectedKeywords": [
      "constructive feedback",
      "code review",
      "learn",
      "discuss",
      "clarify"
    ],
    "sampleAnswerEn": "I view code reviews as an invaluable learning opportunity rather than personal criticism. I would carefully review each comment to understand the underlying rationale, such as performance optimization or design patterns. If any comment requires clarification, I would politely initiate a brief discussion on Slack or schedule a 5-minute call to align on the best architectural approach before submitting revisions.",
    "tipsVi": "💡 Mẹo phỏng vấn: Thể hiện thái độ cầu thị (Growth Mindset), kỹ năng giao tiếp lịch thiệp và tôn trọng tiêu chuẩn chất lượng sản phẩm chung của đội ngũ."
  }
];


export const IELTS_IT_ROADMAP = [
  {
    stageId: "stage_1_a0_a2",
    stageTitle: "Chặng 1: Nền Tảng Bứt Phá (A0 ➔ A2)",
    targetAudience: "Người mất gốc hoàn toàn, bắt đầu từ con số 0",
    cefr: "A0 - A2",
    ieltsTarget: "IELTS 3.0 - 4.5",
    color: "#38BDF8",
    icon: "🌱",
    summary: "Xóa mù tiếng Anh, nắm chắc 44 âm IPA, 100 vật thể công nghệ quanh bạn và 50 câu lệnh lập trình căn bản.",
    modules: [
      {
        id: "m1_1_ipa",
        title: "Bảng Phiên Âm Quốc Tế IPA & Trọng Âm Tiếng Anh Công Nghệ",
        desc: "Luyện phát âm chuẩn 44 âm tiết IPA, phân biệt nguyên âm ngắn/dài (/iː/ vs /ɪ/ trong 'chip' vs 'cheap'), phụ âm rung và bật hơi (/p/, /b/, /t/, /d/).",
        keySkills: ["Phát âm chuẩn IPA 44 âm", "Quy luật trọng âm từ 2 & 3 âm tiết", "Âm đuôi bắt buộc /s/, /z/, /t/, /d/"],
        samplePractice: "Laptop /ˈlæp.tɒp/ (nhấn âm 1) • Server /ˈsɜː.vər/ (nhấn âm 1) • Developer /dɪˈvel.ə.pər/ (nhấn âm 2)",
        actionTip: "Dùng camera AR soi vào laptop, bàn phím, chuột để nghe phát âm IPA và lặp lại theo phương pháp Shadowing."
      },
      {
        id: "m1_2_hardware_vocab",
        title: "Từ Vựng Phần Cứng & Thiết Bị Làm Việc Thực Tế",
        desc: "Nhận biết tên gọi và công dụng của mọi linh kiện và thiết bị xung quanh lập trình viên.",
        keySkills: ["Motherboard", "Central Processing Unit (CPU)", "Graphics Processing Unit (GPU)", "Solid-State Drive (SSD)", "Ethernet Cable"],
        samplePractice: "An SSD drastically reduces operating system boot latency compared to a mechanical hard drive.",
        actionTip: "Mở tính năng AR Lens, quét vào các thiết bị trên bàn làm việc để tích lũy từ vựng trực quan."
      },
      {
        id: "m1_3_programming_logic",
        title: "Từ Vựng Logic Lập Trình Cơ Bản",
        desc: "Cách đọc các câu lệnh điều kiện, vòng lặp, biến số và kiểu dữ liệu bằng tiếng Anh chuẩn xác.",
        keySkills: ["Declare a variable", "Initialize an integer", "Loop through an array", "Conditional branch (if-else)", "Return a value"],
        samplePractice: "This loop iterates over the elements and breaks immediately when a match is encountered.",
        actionTip: "Vào mục Code Lab, chọn bài tập Junior và bấm nghe máy giải thích từng dòng code."
      }
    ]
  },
  {
    stageId: "stage_2_b1_b2",
    stageTitle: "Chặng 2: Giao Tiếp Đội Ngũ Kỹ Thuật (B1 ➔ B2)",
    targetAudience: "Lập trình viên Fresher / Junior bắt đầu làm việc trong môi trường Agile/Scrum",
    cefr: "B1 - B2",
    ieltsTarget: "IELTS 5.0 - 6.5",
    color: "#10B981",
    icon: "🚀",
    summary: "Báo cáo Daily Standup lưu loát, viết Git Commit chuẩn Conventional, đọc hiểu tài liệu API RESTful không cần dịch.",
    modules: [
      {
        id: "m2_1_standup",
        title: "Kỹ Năng Nói Trong Daily Scrum & Sprint Meeting",
        desc: "Cấu trúc 3 câu vàng trong báo cáo hàng ngày: Hôm qua làm gì ➔ Hôm nay làm gì ➔ Có khó khăn (blocker) gì không.",
        keySkills: ["Yesterday I implemented...", "Today I am focusing on...", "I am blocked by missing API endpoints", "Could we sync up after this standup?"],
        samplePractice: "Yesterday I completed the user authentication flow. Today I am drafting unit tests for payment webhook processing. No blockers currently.",
        actionTip: "Mở tab Thực Chiến ➔ Daily Standup Generator để luyện tập nói báo cáo mỗi buổi sáng."
      },
      {
        id: "m2_2_git_commits",
        title: "Viết Git Commit & Pull Request Chuẩn Quốc Tế",
        desc: "Quy chuẩn Conventional Commits (feat, fix, refactor, chore, docs) giúp đồng nghiệp nước ngoài hiểu ngay thay đổi của bạn.",
        keySkills: ["feat(auth): add JWT bearer verification", "fix(db): resolve concurrent connection timeout", "refactor(ui): streamline modal rendering lifecycle"],
        samplePractice: "When writing commit messages, always use imperative mood: 'Add feature' not 'Added feature'.",
        actionTip: "Luyện viết PR description trong tab Thực Chiến với bộ kiểm tra ngữ pháp tự động."
      },
      {
        id: "m2_3_rest_api",
        title: "Đọc & Thảo Luận Tài Liệu API RESTful",
        desc: "Sử dụng chính xác các thuật ngữ về giao thức HTTP, Headers, Payload, Status Code và Query Parameters.",
        keySkills: ["HTTP Methods (GET, POST, PUT, PATCH, DELETE)", "Status Codes (200 OK, 201 Created, 401 Unauthorized, 404 Not Found, 500 Server Error)", "Idempotency"],
        samplePractice: "The endpoint returns a 401 Unauthorized error when the authorization bearer header is omitted.",
        actionTip: "Đọc tài liệu React useEffect và Docker trong tab Tech Docs để nâng cao tốc độ đọc hiểu kỹ thuật."
      }
    ]
  },
  {
    stageId: "stage_3_b2_c1",
    stageTitle: "Chặng 3: Phản Biện Kỹ Thuật & Tối Ưu Hệ Thống (B2 ➔ C1)",
    targetAudience: "Kỹ sư Mid/Senior muốn làm việc cho công ty đa quốc gia, nhận lương nghìn đô",
    cefr: "B2 - C1",
    ieltsTarget: "IELTS 7.0 - 7.5",
    color: "#F59E0B",
    icon: "⚡",
    summary: "Phản biện Code Review lịch thiệp và thuyết phục, phân tích độ phức tạp Big-O, xử lý sự cố Production Incident và viết RCA.",
    modules: [
      {
        id: "m3_1_code_review",
        title: "Nghệ Thuật Phản Biện Code Review (Constructive PR Review)",
        desc: "Cách để lại bình luận mang tính xây dựng, tránh dùng từ ngữ áp đặt và hướng tới giải pháp tối ưu chung.",
        keySkills: ["Have we considered edge cases where...?", "To mitigate race conditions, what if we wrap this in...?", "Nit: Minor typo in variable naming", "LGTM (Looks Good To Me) with a minor suggestion"],
        samplePractice: "Could we potentially extract this utility logic into a dedicated helper function to foster code reusability across modules?",
        actionTip: "Tham khảo tab Phỏng Vấn Vòng 4 để rèn luyện tư duy giao tiếp và phản biện chuyên nghiệp."
      },
      {
        id: "m3_2_big_o",
        title: "Giải Thích Thuật Toán & Độ Phức Tạp Big-O",
        desc: "Trình bày đánh giá Time Complexity và Space Complexity một cách tự tin, chuẩn xác.",
        keySkills: ["Constant time O(1)", "Logarithmic time O(log n)", "Linear time O(n)", "Quadratic time O(n²)", "Worst-case scenario vs Average-case scenario"],
        samplePractice: "By replacing the nested lookup loops with an indexed hash map, we diminish the algorithmic complexity from quadratic O(n²) to linear O(n).",
        actionTip: "Luyện giải thích các đoạn mã trong Code Lab bằng giọng nói tiếng Anh có chấm điểm ngữ điệu."
      },
      {
        id: "m3_3_incident_rca",
        title: "Xử Lý Sự Cố Production & Báo Cáo Root Cause Analysis (RCA)",
        desc: "Cách dùng tiếng Anh bình tĩnh trong phòng họp khẩn cấp (War Room) và viết báo cáo sự cố theo phương pháp 5 Whys.",
        keySkills: ["Service outage", "Latency spike", "Rollback deployment", "Circuit breaker tripped", "Post-mortem documentation"],
        samplePractice: "The cascading outage was triggered by an unindexed database query during peak traffic, precipitating thread pool exhaustion.",
        actionTip: "Thực hành viết Bug Report và Incident Log trong tab Thực Chiến."
      }
    ]
  },
  {
    stageId: "stage_4_c1_c2",
    stageTitle: "Chặng 4: Thiết Kế Kiến Trúc & Chinh Phục IELTS 8.0 (C1 ➔ C2)",
    targetAudience: "Tech Lead, Solution Architect, Kỹ sư chinh phục IELTS Band 8.0+",
    cefr: "C1 - C2",
    ieltsTarget: "IELTS 8.0 - 8.5",
    color: "#8B5CF6",
    icon: "💎",
    summary: "Thuyết trình phỏng vấn System Design cấp cao, viết luận IELTS Writing Task 2 chuyên sâu công nghệ và phản biện IELTS Speaking Part 3 xuất sắc.",
    modules: [
      {
        id: "m4_1_system_design",
        title: "Phỏng Vấn Thiết Kế Hệ Thống Quy Mô Hàng Triệu Người Dùng",
        desc: "Sử dụng các từ vựng học thuật cao cấp về độ tin cậy, thông lượng, tính sẵn sàng và tính phân tán.",
        keySkills: ["Scalability vs Fault-tolerance", "CAP Theorem (Consistency, Availability, Partition Tolerance)", "Database Sharding & Replication", "Distributed Caching (Redis/Memcached)", "Message Brokers & Event-Driven Architecture"],
        samplePractice: "To ensure high availability across geographic regions, we implement an active-active multi-region deployment complemented by distributed consensus protocols.",
        actionTip: "Dùng các cụm từ vựng C1/C2 trong bách khoa toàn thư AR Lens khi mô tả các hệ sinh thái phần mềm."
      },
      {
        id: "m4_2_ielts_writing_tech",
        title: "IELTS Writing Task 2: Chuyên Đề Công Nghệ & Trí Tuệ Nhân Tạo",
        desc: "Xây dựng luận điểm sắc bén về tác động của AI, tự động hóa việc làm, đạo đức dữ liệu và an ninh mạng.",
        keySkills: ["Cohesive devices (Conversely, Notwithstanding, Precipitate, Inherent)", "Hedging (Tương đối hóa luận điểm: tends to suggest, potentially exacerbates)", "Complex sentence subordination"],
        samplePractice: "While proponents argue that ubiquitous artificial intelligence streamlines operational overhead, critics contend that it poses unprecedented algorithmic accountability concerns.",
        actionTip: "Ghi nhớ các câu thành ngữ và cấu trúc C1/C2 đi kèm trong mỗi từ vựng được nhận diện qua Camera."
      },
      {
        id: "m4_3_ielts_speaking_p3",
        title: "IELTS Speaking Part 3: Phản Biện Chuyên Sâu Cấu Trúc P-E-E-L",
        desc: "Công thức trả lời đỉnh cao: Point (Luận điểm) ➔ Explanation (Giải thích cơ chế) ➔ Example (Ví dụ thực tế công nghệ) ➔ Link (Kết nối tổng thể).",
        keySkills: ["From an empirical standpoint...", "This phenomenon is predominantly attributable to...", "A quintessential illustration of this is...", "Consequently, it is imperative that..."],
        samplePractice: "From an architectural perspective, legacy monolithic frameworks inevitably yield to microservice paradigms when developer concurrency exceeds organizational tipping points.",
        actionTip: "Bật nút micro trong Deep Sheet để AI chấm điểm ngữ âm theo thang điểm IELTS Pronunciation."
      }
    ]
  },
  {
    stageId: "stage_5_c2_mastery",
    stageTitle: "Chặng 5: Đỉnh Cao Lãnh Đạo & IELTS 9.0 (C2 Mastery)",
    targetAudience: "Tech Executive, Principal Architect, VP of Engineering, chinh phục IELTS 9.0 tuyệt đối",
    cefr: "C2 Mastery",
    ieltsTarget: "IELTS 8.5 - 9.0",
    color: "#EC4899",
    icon: "👑",
    summary: "Thuyết trình Keynote quốc tế với nghệ thuật tu từ, soạn thảo tiêu chuẩn RFC công nghệ và đàm phán đãi ngộ cấp cao Silicon Valley.",
    modules: [
      {
        id: "m5_1_keynote",
        title: "Nghệ Thuật Diễn Thuyết Công Nghệ (Tech Keynote & Public Speaking)",
        desc: "Kỹ nghệ áp dụng các thủ pháp tu từ tiếng Anh: Điệp từ (Anaphora), Phản đề (Antithesis), Bộ ba nhịp điệu (Rule of Three).",
        keySkills: ["Rhetorical devices for executive persuasion", "Dynamic pitch variation and vocal cadence", "Captivating tech storytelling"],
        samplePractice: "We do not build software simply to digitize processes; we engineer architectures to democratize intelligence, empower communities, and redefine human potential.",
        actionTip: "Nghe câu phát âm mẫu C1/C2 trong AR Lens và bắt chước ngữ điệu nhấn nhá từng âm tiết."
      },
      {
        id: "m5_2_rfc_adr",
        title: "Soạn Thảo Đề Xuất Kỹ Thuật RFC & Architecture Decision Record (ADR)",
        desc: "Ngôn ngữ văn bản mang tính chuẩn mực kỹ thuật cao nhất được áp dụng tại Google, Apple, Microsoft, Amazon.",
        keySkills: ["Executive Summary", "Architectural Trade-offs & Consequences", "Deprecation Strategy", "Security & Compliance Audits"],
        samplePractice: "This Architecture Decision Record articulates our strategic transition towards zero-trust network topology to neutralize perimeter vulnerabilities.",
        actionTip: "Ứng dụng các thuật ngữ IT chuẩn hoá trong cơ sở dữ liệu DevEnglish để hoàn thiện bài viết."
      },
      {
        id: "m5_3_compensation",
        title: "Đàm Phán Lương Bổng Cấp Cao (Senior / Staff / Lead Compensation)",
        desc: "Ngôn ngữ thương thuyết chuyên nghiệp về Total Compensation (Base Salary, Equity Grants, RSUs, Vesting Schedule, Cliff, Sign-on Bonus).",
        keySkills: ["Total compensation benchmarking", "Stock option exercise window", "Accelerated vesting clauses", "Counter-offer negotiation etiquette"],
        samplePractice: "Based on my proven track record in orchestrating cloud migrations and market comp benchmarks, I am targeting a total compensation package commensurate with a Staff-level scope.",
        actionTip: "Luyện tập phỏng vấn trong mục Mock Interview để xây dựng phản xạ giao tiếp tự tin."
      }
    ]
  }
];
