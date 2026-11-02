/**
 * High-Security Multi-Modal AI Vision Engine for DevEnglish AR Lens
 * Powers unlimited real-world object identification with C1/C2 pedagogical synthesis.
 * Built with strict zero-leakage security and client-side encryption.
 */
import { StorageManager } from './storage.js';

export class VisionAiEngine {
  /**
   * Captures high-res JPEG base64 from a video element
   */
  static captureFrameBase64(videoEl, maxWidth = 640) {
    if (!videoEl || !videoEl.videoWidth) return null;
    const canvas = document.createElement('canvas');
    const aspect = videoEl.videoHeight / videoEl.videoWidth;
    canvas.width = Math.min(maxWidth, videoEl.videoWidth);
    canvas.height = canvas.width * aspect;
    const ctx = canvas.getContext('2d');
    ctx.drawImage(videoEl, 0, 0, canvas.width, canvas.height);
    return canvas.toDataURL('image/jpeg', 0.85);
  }

  /**
   * Securely calls Google Gemini 1.5 Flash Vision to identify ANY object in the universe
   */
  static async analyzeWithGemini(base64Image) {
    const apiKey = StorageManager.getGeminiApiKey();
    if (!apiKey) return null;

    const cleanBase64 = base64Image.replace(/^data:image\/(png|jpeg|jpg);base64,/, '');
    const endpoint = `https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=${encodeURIComponent(apiKey)}`;

    const promptText = `Examine this camera image. Identify the primary physical object or real-world subject in view.
Return ONLY a valid JSON object without markdown code blocks, conforming exactly to this structure:
{
  "key": "lowercase_noun",
  "wordEn": "Capitalized Primary English Name",
  "meaningVi": "Nghĩa tiếng Việt chuẩn xác và dễ hiểu",
  "ipa": "/accurate_ipa_phonetics/",
  "partOfSpeech": "danh từ (noun)",
  "cefrLevel": "C1-C2",
  "collocationsEn": "academic collocation 1, professional collocation 2",
  "collocationsVi": "nghĩa tiếng Việt cụm 1, nghĩa tiếng Việt cụm 2",
  "exampleSentenceEn": "An eloquent C1-C2 academic or engineering sentence using this word in context.",
  "exampleSentenceVi": "Bản dịch tiếng Việt hoàn hảo và mượt mà của câu ví dụ trên.",
  "c1c2ExpressionEn": "A high-level pedagogical or technical expression describing this object.",
  "c1c2ExpressionVi": "Giải thích tiếng Việt súc tích của cụm diễn đạt C1/C2 trên.",
  "idiomEn": "A relevant real English idiom or proverb",
  "idiomVi": "Ý nghĩa và ngữ cảnh sử dụng thành ngữ trong tiếng Việt",
  "miniDialogueEn": "A: Dialogue question regarding the object\\nB: Practical insightful response",
  "miniDialogueVi": "A: Lời thoại tiếng Việt\\nB: Lời đáp tiếng Việt",
  "wordFamilyEn": "noun, verb, adjective derivatives",
  "wordFamilyVi": "tương đương tiếng Việt",
  "technicalNoteEn": "Physical, functional, or engineering context.",
  "technicalNoteVi": "Ghi chú kỹ thuật hoặc bối cảnh đời sống thực tế.",
  "quizQuestion": "An engaging quick multiple choice question to test memory of this word?",
  "quizOptions": ["Correct Option", "Distractor 1", "Distractor 2"],
  "quizCorrectIndex": 0,
  "syllableStress": "UPPERCASE-stress"
}`;

    const payload = {
      contents: [{
        parts: [
          { text: promptText },
          {
            inline_data: {
              mime_type: "image/jpeg",
              data: cleanBase64
            }
          }
        ]
      }],
      generationConfig: {
        temperature: 0.2,
        maxOutputTokens: 1024
      }
    };

    const response = await fetch(endpoint, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload)
    });

    if (!response.ok) {
      const errText = await response.text();
      console.warn("Gemini Vision API response error:", response.status, errText);
      return null;
    }

    const jsonRes = await response.json();
    const rawText = jsonRes.candidates?.[0]?.content?.parts?.[0]?.text;
    if (!rawText) return null;

    try {
      const cleanJson = rawText.replace(/```json/gi, '').replace(/```/g, '').trim();
      return JSON.parse(cleanJson);
    } catch (e) {
      console.error("Failed to parse Gemini Vision JSON:", rawText, e);
      return null;
    }
  }
}
