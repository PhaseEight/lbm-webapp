package com.logbookmanager.domain.support;

public class PasswordHint extends ValueObjectSupport<Password> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static String DEFAULT_QUESTION = "What is my Mother's Maiden Name";

	private String question = PasswordHint.DEFAULT_QUESTION;
	private String answer;

	/* required by Hibernate */
	PasswordHint() {
		this.question = null;
	}

	public PasswordHint(String question, String answer) {
		setQuestion(question);
		setAnswer(answer);
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	protected String getQuestion() {
		return question;
	}

	protected void setQuestion(String question) {
		if (question == null) {
			this.question = PasswordHint.DEFAULT_QUESTION;
		} else {
			this.question = question;
		}
	}

}