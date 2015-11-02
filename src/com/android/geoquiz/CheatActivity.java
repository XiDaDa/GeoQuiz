package com.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {

	public static final String EXTRA_ANSWER_IS_TRUE = "com.andriod.geoquiz.answer_is_true";
	public static final String EXTRA_ANSWER_SHOWN = "com.android.geoquiz.answer_shown";
	public static final String EXTRA_QUESTION_INDEX = "com.android.geoquiz.questionindex";
	public static final String CHEATER_QUESTION_INDEX = "com.android.geoquiz.cheaterquestionindex";
	private boolean mAnswerIsTrue;
	private boolean mCheater = false;
	private int mQuestionIndex;
	private int mIsorNot = 0;
	private TextView mAnswerTextView;
	private Button mShowAnswer;
	private int[] questionIndex = new int[5];
	
	protected void setAnswerShowResult(boolean isAnswerShown) {
		Intent data = new Intent();
		Log.d("TTT", "TTT--->" + isAnswerShown);
		data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
		data.putExtra(CHEATER_QUESTION_INDEX, questionIndex);
		setResult(RESULT_OK,data);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cheat);
		if(savedInstanceState != null){
			mCheater = savedInstanceState.getBoolean(EXTRA_ANSWER_SHOWN, false);
		}
		mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
		mQuestionIndex = getIntent().getIntExtra(EXTRA_QUESTION_INDEX, -1);
		mAnswerTextView = (TextView) findViewById(R.id.answerTextView);
		mShowAnswer = (Button) findViewById(R.id.showAnswerButton);
		setAnswerShowResult(mCheater);
		mShowAnswer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mAnswerIsTrue){
					mAnswerTextView.setText(R.string.true_button);
				}else{
					mAnswerTextView.setText(R.string.false_button);
				}
				mCheater = true;
				int i = 0;
				for(int a=0;a<5;a++){
					if(questionIndex[a] == mQuestionIndex){
						mIsorNot = 1;
					}
				}
				if(mIsorNot == 1){
					Log.d("TTT", "TTT---->" + "Done");
				}else{
					questionIndex[i] = mQuestionIndex;
					i++;
				}
				setAnswerShowResult(mCheater);
			}
		});
	}
	
	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState){
		super.onSaveInstanceState(savedInstanceState);
		savedInstanceState.putBoolean(EXTRA_ANSWER_SHOWN, mCheater);
	}
	
}
