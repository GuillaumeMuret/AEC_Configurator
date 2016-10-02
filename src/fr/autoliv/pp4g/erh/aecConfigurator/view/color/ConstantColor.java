/******************************************************************************

AUTOLIV ELECTRONIC document.

-------------------------------------

Copyright Autoliv Inc. All rights reserved.

*******************************************************************************
JAVA-File project AEC_Configurator
******************************************************************************/
/* PRQA S 0288 ++ */
/*
 * Explanation:
 *    see @details
 */
/*
$Revision: 1.0 $
$ProjectName: ?? $
*/
/* PRQA S 0288 -- */
/*!****************************************************************************

@details
	Class where there are all the color used in the program
 */

package fr.autoliv.pp4g.erh.aecConfigurator.view.color;

import java.awt.Color;

public class ConstantColor {
	
	/**
	 * The different color used for COLOR_THEME_BRIGHT
	 */
	public static final Color COLOR_CENTER_PAN_BRIGHT =					Color.white;
	public static final Color COLOR_CENTER_PAN_GRADIENT_BRIGHT=			new Color(0xD6,0xF4,0xFE);
	public static final Color COLOR_CENTER_PAN_BORDER_BRIGHT =			Color.BLACK;
	public static final Color COLOR_TOP_PAN_BRIGHT =					COLOR_CENTER_PAN_BRIGHT;
	public static final Color COLOR_FONT_INFO_BRIGHT =					Color.black;
	public static final Color COLOR_INITIAL_AEC_BRIGHT =				new Color(0x15,0x4A,0xDC);
	public static final Color COLOR_SELECTED_AEC_BRIGHT =				COLOR_INITIAL_AEC_BRIGHT.darker().darker().darker();
	public static final Color COLOR_INPUT_BACKGROUND_BRIGHT =			COLOR_CENTER_PAN_BRIGHT;//new Color(0x00,0x04,0x7B);
	public static final Color COLOR_INPUT_BACKGROUND_GRADIENT_BRIGHT =	COLOR_CENTER_PAN_GRADIENT_BRIGHT;
	
	public static final Color COLOR_ACTION_BAR_TITLE_BRIGHT =			Color.black;
	public static final Color COLOR_SELECTED_STRUCT_LINE_BRIGHT =		new Color(0x45,0x95,0xFD);
	public static final Color COLOR_ERROR_BRIGHT =						Color.red;
	
	public static final Color COLOR_GEN_PATH_MOUSE_ENTER_BRIGHT =		COLOR_SELECTED_STRUCT_LINE_BRIGHT;
	
	public static final Color COLOR_INPUT_FOREGROUND_BRIGHT =			COLOR_SELECTED_AEC_BRIGHT;
	public static final Color COLOR_MOUSE_ENTERED_AEC_BRIGHT =			Color.WHITE.darker().darker();
	public static final Color COLOR_INPUT_BORDER_BRIGHT =				COLOR_CENTER_PAN_BRIGHT.darker().darker();
	public static final Color COLOR_AEC_NAME_LIST_BRIGHT = 				COLOR_FONT_INFO_BRIGHT;
	
	public static final Color COLOR_NEW_LINE_BRIGHT = 					COLOR_CENTER_PAN_GRADIENT_BRIGHT.darker();
	public static final Color COLOR_DIFFERENT_LINE_BRIGHT = 			new Color(0xFF,0x52,0x52);
	
	public static final Color COLOR_BUTTON_MOUSE_ENTERED_BRIGHT = 		COLOR_CENTER_PAN_BRIGHT.darker();
	
	
	/**
	 * The different color used for COLOR_THEME_DARK
	 */
	public static final Color COLOR_CENTER_PAN_DARK =					new Color(0x20,0x20,0x20);
	public static final Color COLOR_CENTER_PAN_GRADIENT_DARK=			new Color(0x03,0x03,0x26);//Color.black;
	public static final Color COLOR_CENTER_PAN_BORDER_DARK =			Color.WHITE;
	public static final Color COLOR_TOP_PAN_DARK =						COLOR_CENTER_PAN_DARK;
	public static final Color COLOR_FONT_INFO_DARK =					Color.white;
	public static final Color COLOR_INITIAL_AEC_DARK =					new Color(0x15,0x4A,0xDC);
	public static final Color COLOR_SELECTED_AEC_DARK =					COLOR_INITIAL_AEC_DARK.brighter().brighter().brighter();
	public static final Color COLOR_INPUT_BACKGROUND_DARK =				COLOR_CENTER_PAN_DARK;
	public static final Color COLOR_INPUT_BACKGROUND_GRADIENT_DARK =	COLOR_CENTER_PAN_GRADIENT_DARK;
	
	public static final Color COLOR_ACTION_BAR_TITLE_DARK =				Color.white;
	public static final Color COLOR_SELECTED_STRUCT_LINE_DARK =			new Color(0x2B,0x32,0x9C);
	public static final Color COLOR_ERROR_DARK =						Color.red;
	
	public static final Color COLOR_GEN_PATH_MOUSE_ENTER_DARK =			COLOR_SELECTED_STRUCT_LINE_DARK;
	
	public static final Color COLOR_INPUT_FOREGROUND_DARK =				COLOR_SELECTED_AEC_DARK;
	public static final Color COLOR_MOUSE_ENTERED_AEC_DARK =			Color.WHITE.darker().darker();
	public static final Color COLOR_INPUT_BORDER_DARK =					COLOR_CENTER_PAN_DARK.brighter().brighter().brighter().brighter().brighter();
	public static final Color COLOR_AEC_NAME_LIST_DARK = 				COLOR_FONT_INFO_DARK;
	
	public static final Color COLOR_NEW_LINE_DARK = 					COLOR_SELECTED_STRUCT_LINE_DARK;
	public static final Color COLOR_DIFFERENT_LINE_DARK = 				new Color(0xFF,0x52,0x52);
	
	public static final Color COLOR_BUTTON_MOUSE_ENTERED_DARK = 		COLOR_CENTER_PAN_DARK.brighter().brighter().brighter();

}	
