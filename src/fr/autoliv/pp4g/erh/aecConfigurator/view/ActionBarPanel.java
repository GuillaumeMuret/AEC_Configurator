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
	Class of the Action bar where the button and the title are displayed
 */

package fr.autoliv.pp4g.erh.aecConfigurator.view;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.ControlStore;
import fr.autoliv.pp4g.erh.aecConfigurator.model.file.FileStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.color.ColorStore;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantDimension;
import fr.autoliv.pp4g.erh.aecConfigurator.view.constant.ConstantFont;

public class ActionBarPanel extends JPanel{
	
	/**
	 * The different state of the screen
	 */
	public static final int STATE_EDIT_VALUE 		= 1;
	public static final int STATE_EDIT_AEC 			= 2;
	public static final int STATE_NEW_AEC 			= 3;
	public static final int STATE_CONFIGURATION 	= 4;
	public static final int STATE_DETAIL_LIST		= 5;
	public static final int STATE_SHOW_DIFFERENCE	= 6;
	
	public static final int STATE_MANAGE_GROUP		= 7;
	
	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The icon panel : where the icon are displayed
	 */
	private JPanel iconPanel;
	
	/**
	 * The sub-top label : where the state name is displayed
	 */
	private JLabel subtopLabel;
	
	/**
	 * Constructor of the action bar panel
	 * @param mainFrame
	 */
	public ActionBarPanel(MainFrame mainFrame){
		super();
		this.setLayout(new BorderLayout());
		this.setOpaque(true);
		this.setBackground(ColorStore.getInstance().getColorCenterPan());
        this.setPreferredSize(ConstantDimension.PANEL_ACTION_BAR);
        this.setMinimumSize(ConstantDimension.PANEL_ACTION_BAR);
        this.setMaximumSize(ConstantDimension.PANEL_ACTION_BAR);
        this.setBorder(BorderFactory.createLineBorder(ColorStore.getInstance().getColorFontInfo()));
        
        this.subtopLabel = new JLabel("  Ready to generate  ");
        this.subtopLabel.setOpaque(false);
        this.subtopLabel.setFont(ConstantFont.FONT_TITLE_ACTION_BAR);
        this.subtopLabel.setForeground(ColorStore.getInstance().getColorActionBarTitle());        
        
        this.iconPanel = new JPanel();
        this.iconPanel.setLayout(new BoxLayout(this.iconPanel,BoxLayout.LINE_AXIS));
        this.iconPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.iconPanel.setOpaque(false);
        this.iconPanel.setBorder(BorderFactory.createEmptyBorder());
        
        this.add(this.subtopLabel,BorderLayout.WEST);
        this.add(this.iconPanel,BorderLayout.EAST);
	}
	
	/**
	 * Setter of the action bar panel : display the icon (depends on the state machine)
	 * @param state
	 */
	public void updateActionBarPanel(int state){
		this.iconPanel.removeAll();
		switch(state){
		
			case STATE_EDIT_VALUE:
				this.subtopLabel.setText("  Edit values  ");
				
				this.iconPanel.add(ControlStore.getInstance().getButtonRefresh());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR*3));
				this.iconPanel.add(ControlStore.getInstance().getButtonSearch());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR*3));
				this.iconPanel.add(ControlStore.getInstance().getButtonDeleteComponent());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR*3));
				
		        this.iconPanel.add(ControlStore.getInstance().getButtonAddAttribute());
		        this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
		        this.iconPanel.add(ControlStore.getInstance().getButtonAddAEC());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR*5));
				this.iconPanel.add(ControlStore.getInstance().getButtonArrowUp());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
				this.iconPanel.add(ControlStore.getInstance().getButtonArrowDown());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR*5));
		        this.iconPanel.add(ControlStore.getInstance().getButtonList());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
				this.iconPanel.add(ControlStore.getInstance().getButtonGenerate());   
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
		        this.iconPanel.add(ControlStore.getInstance().getButtonSetting());
		        this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
				break;
				
			case STATE_EDIT_AEC:
				this.subtopLabel.setText("  Edit component  ");
				
				this.iconPanel.add(ControlStore.getInstance().getButtonRefresh());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR*3));
				this.iconPanel.add(ControlStore.getInstance().getButtonSearch());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR*3));
				this.iconPanel.add(ControlStore.getInstance().getButtonDeleteAttribute());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
				this.iconPanel.add(ControlStore.getInstance().getButtonDeleteComponent());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR*5));
		        this.iconPanel.add(ControlStore.getInstance().getButtonAddAttribute());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
		        this.iconPanel.add(ControlStore.getInstance().getButtonAddAEC());
		        this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR*5));
		        this.iconPanel.add(ControlStore.getInstance().getButtonList());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
				this.iconPanel.add(ControlStore.getInstance().getButtonGenerate());   
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
		        this.iconPanel.add(ControlStore.getInstance().getButtonSetting());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR*5));
				this.iconPanel.add(ControlStore.getInstance().getButtonEscape());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
				break;
				
			case STATE_NEW_AEC:
				this.subtopLabel.setText("  New component  ");
				
				this.iconPanel.add(ControlStore.getInstance().getButtonRefresh());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR*3));
				this.iconPanel.add(ControlStore.getInstance().getButtonSearch());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR*3));
				this.iconPanel.add(ControlStore.getInstance().getButtonDeleteAttribute());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
				this.iconPanel.add(ControlStore.getInstance().getButtonDeleteComponent());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR*5));
		        this.iconPanel.add(ControlStore.getInstance().getButtonAddAttribute());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
		        this.iconPanel.add(ControlStore.getInstance().getButtonAddAEC());
		        this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR*5));
		        this.iconPanel.add(ControlStore.getInstance().getButtonList());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
				this.iconPanel.add(ControlStore.getInstance().getButtonGenerate());   
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
		        this.iconPanel.add(ControlStore.getInstance().getButtonSetting());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR*5));
				this.iconPanel.add(ControlStore.getInstance().getButtonEscape());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
				break;
				
			case STATE_CONFIGURATION:
				this.subtopLabel.setText("  Generation param  ");
				
				this.iconPanel.add(ControlStore.getInstance().getButtonRefresh());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR*3));
				this.iconPanel.add(ControlStore.getInstance().getButtonDeleteAttribute());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
				this.iconPanel.add(ControlStore.getInstance().getButtonAddAttribute());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR*5));
				this.iconPanel.add(ControlStore.getInstance().getButtonArrowUp());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
				this.iconPanel.add(ControlStore.getInstance().getButtonArrowDown());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR*5));
				this.iconPanel.add(ControlStore.getInstance().getButtonList());	
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
				this.iconPanel.add(ControlStore.getInstance().getButtonGenerate());  
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
				this.iconPanel.add(ControlStore.getInstance().getButtonEscape());	
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
				break;
				
			case STATE_DETAIL_LIST:
				this.subtopLabel.setText("  List  ");
				
				this.iconPanel.add(ControlStore.getInstance().getButtonRefresh());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR*3));
		        this.iconPanel.add(ControlStore.getInstance().getButtonAddAttribute());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
		        this.iconPanel.add(ControlStore.getInstance().getButtonAddAEC());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
				this.iconPanel.add(ControlStore.getInstance().getButtonGenerate());  
		        this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR*5));
		        this.iconPanel.add(ControlStore.getInstance().getButtonSetting());
		        this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR*7));
				this.iconPanel.add(ControlStore.getInstance().getButtonEscape());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
				break;
				
				
			case STATE_SHOW_DIFFERENCE:
				this.subtopLabel.setText("  Show differences  : "+FileStore.getInstance().getFileTitle());
				
				this.iconPanel.add(ControlStore.getInstance().getButtonScrollDown());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
				this.iconPanel.add(ControlStore.getInstance().getButtonScrollUp());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
		        this.iconPanel.add(ControlStore.getInstance().getButtonPreviousPage());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
				this.iconPanel.add(ControlStore.getInstance().getButtonNextPage());  
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR*7));
				this.iconPanel.add(ControlStore.getInstance().getButtonEscape());
				this.iconPanel.add(Box.createHorizontalStrut(ConstantDimension.SEPARATOR));
		}
	}
	
	/**
	 * Process called to update the action bar title
	 */
	public void updatActionBarTitle(){
		this.subtopLabel.setText("  Show differences  : "+FileStore.getInstance().getFileTitle());
	}
	
	/**
	 * Getter of the title label
	 */
	public JLabel getTitleLabel(){
		return this.subtopLabel;
	}
}
