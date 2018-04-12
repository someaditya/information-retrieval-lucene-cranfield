package com.some.lucene;


import java.io.Reader;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.StopwordAnalyzerBase;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;


public class MyAnalyzer extends StopwordAnalyzerBase {

	private int maxTokenLength = 255;

	public MyAnalyzer(CharArraySet stopwords) {
		super(stopwords);
	}

	public void setMaxTokenLength(int length) {
		maxTokenLength = length;
	}

	public int getMaxTokenLength() {
		return maxTokenLength;
	}
	
	public CharArraySet getStopwords() {
		return stopwords;
	}

	@Override
	protected TokenStreamComponents createComponents(final String fieldName) {
		final Tokenizer src;
	    StandardTokenizer t = new StandardTokenizer(); //the standard tokenizer of lucene
	    t.setMaxTokenLength(maxTokenLength);
	    src = t;
	    
	    TokenStream tok = new StandardFilter(src);	
	    tok = new LowerCaseFilter(tok); 
	    tok= new PorterStemFilter(tok);
	    tok = new StopFilter(tok, StopAnalyzer.ENGLISH_STOP_WORDS_SET); 
	    return new TokenStreamComponents(src, tok) {
	      @Override
	      protected void setReader(final Reader reader) {
	        int m = MyAnalyzer.this.maxTokenLength;
	        ((StandardTokenizer)src).setMaxTokenLength(m);
	        super.setReader(reader);
	      }
	    };
	}

}
