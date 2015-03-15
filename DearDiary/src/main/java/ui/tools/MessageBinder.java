package ui.tools;

import org.springframework.ui.Model;

public class MessageBinder {


	public enum MessageTypes {
	    HANDLED_ERROR("handledError");

	    private final String text;

	    /**
	     * @param text
	     */
	    private MessageTypes(final String text) {
	        this.text = text;
	    }

	    /* (non-Javadoc)
	     * @see java.lang.Enum#toString()
	     */
	    @Override
	    public String toString() {
	        return text;
	    }
	}
	
	public static void bindErrorMessage(Model model, String message){
		model.addAttribute(MessageTypes.HANDLED_ERROR.toString(), message);
	}
}
