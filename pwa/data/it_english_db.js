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
