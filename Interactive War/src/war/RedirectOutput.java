package war;

import java.io.IOException;

import javax.swing.JTextArea;

/**
 * RedirectOutput Class - Directs print statements to JTextArea
 * 
 * @author Travis Truong
 *
 */
public class RedirectOutput extends java.io.OutputStream {
	
	private JTextArea outputTextArea;
	
	public RedirectOutput(JTextArea outputTextArea) {
		this.outputTextArea = outputTextArea;
	}

	@Override
	public void write(int b) throws IOException {
		this.outputTextArea.append(String.valueOf((char) b));
		this.outputTextArea.setCaretPosition(this.outputTextArea.getDocument().getLength());
	}
}
