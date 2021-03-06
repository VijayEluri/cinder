package org.art_core.dev.cinder.prefs;

import java.util.ResourceBundle;

import org.eclipse.jface.preference.*;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.art_core.dev.cinder.CinderPlugin;

/**
 * This class represents a preference page that is contributed to the
 * Preferences dialog. By subclassing <samp>FieldEditorPreferencePage</samp>, we
 * can use the field support built into JFace that allows us to create a page
 * that is small and knows how to save, restore and apply itself.
 * <p>
 * This page is used to modify preferences only. They are stored in the
 * preference store that belongs to the main plug-in class. That way,
 * preferences can be accessed directly via the preference store.
 */

public class CinderPrefPage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	private ResourceBundle cRes = ResourceBundle.getBundle("org.art_core.dev.cinder.CinderResource");
	
	public static final String P_PATH = "pathPreference";
	public static final String P_BOOLEAN = "booleanPreference";
	public static final String P_CHOICE = "choicePreference";
	public static final String P_STRING = "stringPreference";
	public static final String P_COLOR = "colorPreference";
	public static final String P_INTEGER = "integerPreference";
	
	public static final int STRING_FIELD = 1;
	public static final int FILE_FIELD = 2;
	
	public CinderPrefPage() {
		super(GRID);
		setPreferenceStore(CinderPlugin.getDefault().getPreferenceStore());
		setDescription(cRes.getString("GENERAL_SETTINGS"));
		setMessage(cRes.getString("PREFERENCES"));
	}

	/**
	 * Creates the field editors. Field editors are abstractions of the common
	 * GUI blocks needed to manipulate various types of preferences. Each field
	 * editor knows how to save and restore itself.
	 */
	public void createFieldEditors() {
		addImportSource(STRING_FIELD, "xml_url", 1);
		addImportSource(STRING_FIELD, "xml_url", 2);
		addImportSource(STRING_FIELD, "xml_url", 3);
		addImportSource(FILE_FIELD, "xml_file", 1);
		addImportSource(FILE_FIELD, "xml_file", 2);
		addImportSource(FILE_FIELD, "xml_file", 3);
		
		addField(new BooleanFieldEditor(CinderPrefPage.P_BOOLEAN + "_show_debug", 
				cRes.getString("SHOW_DEBUG"), getFieldEditorParent()));
	}

	private void addImportSource(int iMode, String sIdentifier, int iNumber) {
		String sNameString = CinderPrefPage.P_STRING + "_" + sIdentifier + "_" + iNumber;
		String sNameInt = CinderPrefPage.P_INTEGER + "_" + sIdentifier + "_" + iNumber;
		String sNameBool = CinderPrefPage.P_BOOLEAN + "_" + sIdentifier + "_" + iNumber;
		
		if (iMode == FILE_FIELD) {
			addField(new FileFieldEditor(sNameString,
				cRes.getString("XML_FILE"), getFieldEditorParent()));
		} else {
			addField(new StringFieldEditor(sNameString,
					cRes.getString("XML_URL"), getFieldEditorParent()));
		}
		addField(new BooleanFieldEditor(sNameBool + "_check", 
				cRes.getString("CHECK_PERIODICALLY"), getFieldEditorParent()));
		IntegerFieldEditor ifeUrl = new IntegerFieldEditor(sNameInt + "_time", 
				cRes.getString("INTERVAL_IN_MINUTES"), getFieldEditorParent());
		ifeUrl.setTextLimit(3);
		ifeUrl.setValidRange(1, 999);
		addField(ifeUrl);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

}