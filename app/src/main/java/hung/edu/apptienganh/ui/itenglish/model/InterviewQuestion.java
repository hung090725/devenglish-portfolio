package hung.edu.apptienganh.ui.itenglish.model;

import java.util.List;

public class InterviewQuestion {
    private String id;
    private String role;       // Java, Web, AI, QA
    private String round;      // Round 1: Intro, Round 2: Technical, Round 3: Problem Solving, Round 4: Teamwork
    private String questionEn;
    private String questionVi;
    private List<String> expectedKeywords;
    private String sampleAnswerEn;
    private String interviewTipsVi;

    public InterviewQuestion(String id, String role, String round, String questionEn, String questionVi,
                             List<String> expectedKeywords, String sampleAnswerEn, String interviewTipsVi) {
        this.id = id;
        this.role = role;
        this.round = round;
        this.questionEn = questionEn;
        this.questionVi = questionVi;
        this.expectedKeywords = expectedKeywords;
        this.sampleAnswerEn = sampleAnswerEn;
        this.interviewTipsVi = interviewTipsVi;
    }

    public String getId() { return id; }
    public String getRole() { return role; }
    public String getRound() { return round; }
    public String getQuestionEn() { return questionEn; }
    public String getQuestionVi() { return questionVi; }
    public List<String> getExpectedKeywords() { return expectedKeywords; }
    public String getSampleAnswerEn() { return sampleAnswerEn; }
    public String getInterviewTipsVi() { return interviewTipsVi; }
}
