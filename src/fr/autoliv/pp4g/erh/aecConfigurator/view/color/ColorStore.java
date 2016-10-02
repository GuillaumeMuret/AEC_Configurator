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
	Class color store where the different color are get
 */

package fr.autoliv.pp4g.erh.aecConfigurator.view.color;

import java.awt.Color;


public class ColorStore {
	
	public static final int COLOR_THEME_BRIGHT = 1;
	public static final int COLOR_THEME_DARK   = 2;
	
	private Color colorCenterPan;
	private Color colorCenterPanGradient;
	private Color colorCenterPanBorder;
	private Color colorTopPan;
	private Color colorFontInfo;
	private Color colorInitialAEC;
	private Color colorSelectedAEC;
	private Color colorInputBackground;
	private Color colorInputBackgroundGradient;
	private Color colorActionBarTitle;
	private Color colorSelectedStructLine;
	private Color colorError;
	private Color colorGenPathMouseEnter;
	private Color colorInputForeground;
	private Color colorMouseEnteredAEC;
	private Color colorInputBorder;
	private Color colorAECnameList;
	private Color colorNewLine;
	private Color colorDifferentLine;
	
	/**
     * Singleton management
     */
    private static ColorStore instance;    
    
    /**
     * Getter of the instance DataStore
     * @return the instance DataStore
     */
    public static ColorStore getInstance () {
        if (instance == null){
            instance = new ColorStore();
        }
        return instance;
    }
    
    private int colorTheme;
    
    public void setColorTheme(int newColorTheme){
    	this.colorTheme=newColorTheme;
    	getColorActionBarTitle();
    	getColorAECnameList();
    	getColorCenterPan();
    	getColorCenterPanBorder();
    	getColorCenterPanGradient();
    	getColorDifferentLine();
    	getColorError();
    	getColorFontInfo();
    	getColorGenPathMouseEnter();
    	getColorInitialAEC();
    	getColorInputBackground();
    	getColorInputBackgroundGradient();
    	getColorInputBorder();
    	getColorInputForeground();
    	getColorMouseEnteredAEC();
    	getColorNewLine();
    	getColorSelectedAEC();
    	getColorSelectedStructLine();
    	getColorTopPan();
    }
    
    public Color getColorCenterPan(){
    	if(colorTheme==COLOR_THEME_BRIGHT){
    		this.colorCenterPan=ConstantColor.COLOR_CENTER_PAN_BRIGHT;
    	}
    	if(colorTheme == COLOR_THEME_DARK){
    		this.colorCenterPan=ConstantColor.COLOR_CENTER_PAN_DARK;
    	}
    	return this.colorCenterPan;
    }
    
    public Color getColorCenterPanGradient(){
    	if(colorTheme==COLOR_THEME_BRIGHT){
    		this.colorCenterPanGradient = ConstantColor.COLOR_CENTER_PAN_GRADIENT_BRIGHT;
    	}
    	if(colorTheme == COLOR_THEME_DARK){
    		this.colorCenterPanGradient = ConstantColor.COLOR_CENTER_PAN_GRADIENT_DARK;
    	}
    	return this.colorCenterPanGradient;
    }
    
    public Color getColorCenterPanBorder(){
    	if(colorTheme==COLOR_THEME_BRIGHT){
    		this.colorCenterPanBorder=ConstantColor.COLOR_CENTER_PAN_BORDER_BRIGHT;
    	}
    	if(colorTheme == COLOR_THEME_DARK){
    		this.colorCenterPanBorder=ConstantColor.COLOR_CENTER_PAN_BORDER_DARK;
    	}
    	return this.colorCenterPanBorder;
    }
    
    public Color getColorTopPan(){
    	if(colorTheme==COLOR_THEME_BRIGHT){
    		this.colorTopPan= ConstantColor.COLOR_TOP_PAN_BRIGHT;
    	}
    	if(colorTheme == COLOR_THEME_DARK){
    		this.colorTopPan= ConstantColor.COLOR_TOP_PAN_DARK;
    	}
    	return this.colorTopPan;
    }
    
    public Color getColorFontInfo(){
    	if(colorTheme==COLOR_THEME_BRIGHT){
    		this.colorFontInfo= ConstantColor.COLOR_FONT_INFO_BRIGHT;
    	}
    	if(colorTheme == COLOR_THEME_DARK){
    		this.colorFontInfo= ConstantColor.COLOR_FONT_INFO_DARK;
    	}
    	return this.colorFontInfo;
    }
    
    public Color getColorInitialAEC(){
    	if(colorTheme==COLOR_THEME_BRIGHT){
    		this.colorInitialAEC= ConstantColor.COLOR_INITIAL_AEC_BRIGHT;
    	}
    	if(colorTheme == COLOR_THEME_DARK){
    		this.colorInitialAEC= ConstantColor.COLOR_INITIAL_AEC_DARK;
    	}
    	return this.colorInitialAEC;
    }
    
    public Color getColorSelectedAEC(){
    	if(colorTheme==COLOR_THEME_BRIGHT){
    		this.colorSelectedAEC= ConstantColor.COLOR_SELECTED_AEC_BRIGHT;
    	}
    	if(colorTheme == COLOR_THEME_DARK){
    		this.colorSelectedAEC= ConstantColor.COLOR_SELECTED_AEC_DARK;
    	}
    	return this.colorSelectedAEC;
    }
    
    public Color getColorInputBackground(){
    	if(colorTheme==COLOR_THEME_BRIGHT){
    		this.colorInputBackground= ConstantColor.COLOR_INPUT_BACKGROUND_BRIGHT;
    	}
    	if(colorTheme == COLOR_THEME_DARK){
    		this.colorInputBackground= ConstantColor.COLOR_INPUT_BACKGROUND_DARK;
    	}
    	return this.colorInputBackground;
    }
    
    public Color getColorInputBackgroundGradient(){
    	if(colorTheme==COLOR_THEME_BRIGHT){
    		this.colorInputBackgroundGradient= ConstantColor.COLOR_INPUT_BACKGROUND_GRADIENT_BRIGHT;
    	}
    	if(colorTheme == COLOR_THEME_DARK){
    		this.colorInputBackgroundGradient= ConstantColor.COLOR_INPUT_BACKGROUND_GRADIENT_DARK;
    	}
    	return this.colorInputBackgroundGradient;
    }
    
    public Color getColorActionBarTitle(){
    	if(colorTheme==COLOR_THEME_BRIGHT){
    		this.colorActionBarTitle= ConstantColor.COLOR_ACTION_BAR_TITLE_BRIGHT;
    	}
    	if(colorTheme == COLOR_THEME_DARK){
    		this.colorActionBarTitle= ConstantColor.COLOR_ACTION_BAR_TITLE_DARK;
    	}
    	return this.colorActionBarTitle;
    }
    
    public Color getColorSelectedStructLine(){
    	if(colorTheme==COLOR_THEME_BRIGHT){
    		this.colorSelectedStructLine = ConstantColor.COLOR_SELECTED_STRUCT_LINE_BRIGHT;
    	}
    	if(colorTheme == COLOR_THEME_DARK){
    		this.colorSelectedStructLine = ConstantColor.COLOR_SELECTED_STRUCT_LINE_DARK;
    	}
    	return this.colorSelectedStructLine; 
    }
    
    public Color getColorError(){
    	if(colorTheme==COLOR_THEME_BRIGHT){
    		this.colorError= ConstantColor.COLOR_ERROR_BRIGHT;
    	}
    	if(colorTheme == COLOR_THEME_DARK){
    		this.colorError= ConstantColor.COLOR_ERROR_DARK;
    	}
    	return this.colorError;
    }
    
    public Color getColorGenPathMouseEnter(){
    	if(colorTheme==COLOR_THEME_BRIGHT){
    		this.colorGenPathMouseEnter= ConstantColor.COLOR_GEN_PATH_MOUSE_ENTER_BRIGHT;
    	}
    	if(colorTheme == COLOR_THEME_DARK){
    		this.colorGenPathMouseEnter= ConstantColor.COLOR_GEN_PATH_MOUSE_ENTER_DARK;
    	}
    	return this.colorGenPathMouseEnter;
    }
    
    public Color getColorInputForeground(){
    	if(colorTheme==COLOR_THEME_BRIGHT){
    		this.colorInputForeground=ConstantColor.COLOR_INPUT_FOREGROUND_BRIGHT;
    	}
    	if(colorTheme == COLOR_THEME_DARK){
    		this.colorInputForeground=ConstantColor.COLOR_INPUT_FOREGROUND_DARK;
    	}
    	return this.colorInputForeground;
    }
    
    public Color getColorMouseEnteredAEC(){
    	if(colorTheme==COLOR_THEME_BRIGHT){
    		this.colorMouseEnteredAEC= ConstantColor.COLOR_MOUSE_ENTERED_AEC_BRIGHT;
    	}
    	if(colorTheme == COLOR_THEME_DARK){
    		this.colorMouseEnteredAEC= ConstantColor.COLOR_MOUSE_ENTERED_AEC_DARK;
    	}
    	return this.colorMouseEnteredAEC;
    }
    
    public Color getColorInputBorder(){
    	if(colorTheme==COLOR_THEME_BRIGHT){
    		this.colorInputBorder = ConstantColor.COLOR_INPUT_BORDER_BRIGHT;
    	}
    	if(colorTheme == COLOR_THEME_DARK){
    		this.colorInputBorder = ConstantColor.COLOR_INPUT_BORDER_DARK;
    	}
    	return this.colorInputBorder;
    }
    
    public Color getColorAECnameList(){
    	if(colorTheme==COLOR_THEME_BRIGHT){
    		this.colorAECnameList = ConstantColor.COLOR_AEC_NAME_LIST_BRIGHT;
    	}
    	if(colorTheme == COLOR_THEME_DARK){
    		this.colorAECnameList = ConstantColor.COLOR_AEC_NAME_LIST_DARK;
    	}
    	return this.colorAECnameList;
    }
    
    public Color getColorNewLine(){
    	if(colorTheme==COLOR_THEME_BRIGHT){
    		this.colorNewLine = ConstantColor.COLOR_NEW_LINE_BRIGHT;
    	}
    	if(colorTheme == COLOR_THEME_DARK){
    		this.colorNewLine = ConstantColor.COLOR_NEW_LINE_DARK;
    	}
    	return this.colorNewLine;
    }
    
    public Color getColorDifferentLine(){
    	if(colorTheme==COLOR_THEME_BRIGHT){
    		this.colorDifferentLine= ConstantColor.COLOR_DIFFERENT_LINE_BRIGHT;
    	}
    	if(colorTheme == COLOR_THEME_DARK){
    		this.colorDifferentLine= ConstantColor.COLOR_DIFFERENT_LINE_DARK;
    	}
    	return this.colorDifferentLine;
    }
    
    public Color getColorButtonMouseEntered(){
    	if(colorTheme==COLOR_THEME_BRIGHT){
    		this.colorDifferentLine= ConstantColor.COLOR_BUTTON_MOUSE_ENTERED_BRIGHT;
    	}
    	if(colorTheme == COLOR_THEME_DARK){
    		this.colorDifferentLine= ConstantColor.COLOR_BUTTON_MOUSE_ENTERED_DARK;
    	}
    	return this.colorDifferentLine;
    }
}