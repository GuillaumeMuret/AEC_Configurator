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
	Class which has all the control of the screen
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.button.ButtonAddAEC;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.button.ButtonAddAttribute;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.button.ButtonAddGroup;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.button.ButtonArrowDown;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.button.ButtonArrowUp;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.button.ButtonDeleteAttribute;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.button.ButtonDeleteComponent;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.button.ButtonDeleteGroup;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.button.ButtonEdit;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.button.ButtonEscape;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.button.ButtonGenerate;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.button.ButtonList;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.button.ButtonNextPage;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.button.ButtonPreviousPage;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.button.ButtonRefresh;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.button.ButtonSave;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.button.ButtonScrollDown;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.button.ButtonScrollUp;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.button.ButtonSearch;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.button.ButtonSetting;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.button.ButtonTakeGroupDown;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.button.ButtonTakeGroupUp;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.Executor;
import fr.autoliv.pp4g.erh.aecConfigurator.view.dialog.DialogStore;



public class ControlStore {
	/**
     * Singleton management
     */
    private static ControlStore instance;    
    
    /**
     * Getter of the instance DataStore
     * @return the instance DataStore
     */
    public static ControlStore getInstance () {
        if (instance == null){
            instance = new ControlStore();
            instance.initButton();
        }
        
        return instance;
    }
    
    private void initButton(){
    	this.buttonAddAEC = new ButtonAddAEC(DialogStore.getInstance().getMainFrame());
		this.buttonAddAttribute = new ButtonAddAttribute(DialogStore.getInstance().getMainFrame());
		this.buttonArrowDown = new ButtonArrowDown(DialogStore.getInstance().getMainFrame());
		this.buttonArrowUp = new ButtonArrowUp(DialogStore.getInstance().getMainFrame());
		this.buttonDeleteComponent = new ButtonDeleteComponent(DialogStore.getInstance().getMainFrame());
		this.buttonDeleteAttribute = new ButtonDeleteAttribute(DialogStore.getInstance().getMainFrame());
		this.buttonEscape = new ButtonEscape(DialogStore.getInstance().getMainFrame());
		this.buttonGenerate = new ButtonGenerate(DialogStore.getInstance().getMainFrame());
		this.buttonSetting = new ButtonSetting(DialogStore.getInstance().getMainFrame());
		this.buttonList = new ButtonList(DialogStore.getInstance().getMainFrame());
		this.buttonRefresh = new ButtonRefresh(DialogStore.getInstance().getMainFrame());
		this.buttonNextPage = new ButtonNextPage(DialogStore.getInstance().getMainFrame());
		this.buttonPreviousPage = new ButtonPreviousPage(DialogStore.getInstance().getMainFrame());
		this.buttonScrollDown = new ButtonScrollDown(DialogStore.getInstance().getMainFrame());
		this.buttonScrollUp = new ButtonScrollUp(DialogStore.getInstance().getMainFrame());
		this.buttonSearch = new ButtonSearch(DialogStore.getInstance().getMainFrame());
		this.buttonEdit = new ButtonEdit(DialogStore.getInstance().getMainFrame());
		this.buttonSave = new ButtonSave(DialogStore.getInstance().getMainFrame());
		this.buttonTakeGroupDown = new ButtonTakeGroupDown(DialogStore.getInstance().getMainFrame());
		this.buttonTakeGroupUp = new ButtonTakeGroupUp(DialogStore.getInstance().getMainFrame());
		this.buttonDeleteGroup = new ButtonDeleteGroup(DialogStore.getInstance().getMainFrame());
		this.buttonAddGroup = new ButtonAddGroup(DialogStore.getInstance().getMainFrame());
    }
    
    ////// CTRL key pressed boolean //////
    /**
     * Object CTRL key
     */
    private boolean ctrlKeyPressed;

    /**
     * Setter of the CTRL key pressed
     * @param state
     */
    public void setCtrlKeyPressed(boolean state){
        this.ctrlKeyPressed=state;
    }

    /**
     * Getter of the NVP component list
     * @return
     */
    public boolean getCtrlKeyPressed(){
        return this.ctrlKeyPressed;
    }
    
    /**
     * The executor of the command pattern
     */
    private Executor executor;
    
    /**
     * Setter of the Executor
     * @param executor
     */
    public void setExecutor(Executor executor){
    	this.executor=executor;
    }
    
    /**
     * Getter of the executor
     * @return
     */
    public Executor getExecutor(){
    	return this.executor;
    }
    
    /**
     * Button add group
     */
    private ButtonAddGroup buttonAddGroup;
    
    /**
     * Button delete group
     */
    private ButtonDeleteGroup buttonDeleteGroup;
    
    /**
     * Button take group down
     */
    private ButtonTakeGroupDown buttonTakeGroupDown;
    
    /**
     * Button take group up
     */
    private ButtonTakeGroupUp buttonTakeGroupUp;
    
    /**
     * Object button edit
     */
    private ButtonEdit buttonEdit;
    
    /**
     * Object button save
     */
    private ButtonSave buttonSave;

	/**
	 * The button add AEC
	 */
	private ButtonAddAEC buttonAddAEC;
	
	/**
	 * The button add Attribute
	 */
	private ButtonAddAttribute buttonAddAttribute;
	
	/**
	 * The button arrow down
	 */
	private ButtonArrowDown buttonArrowDown;
	
	/**
	 * The button arrow up
	 */
	private ButtonArrowUp buttonArrowUp;
	
	/**
	 * The button delete component
	 */
	private ButtonDeleteComponent buttonDeleteComponent;
	
	/**
	 * The button delete attribute
	 */
	private ButtonDeleteAttribute buttonDeleteAttribute;
	
	/**
	 * The button escape
	 */
	private ButtonEscape buttonEscape;
	
	/**
	 * The button generate
	 */
	private ButtonGenerate buttonGenerate;
	
	/**
	 * The button setting
	 */
	private ButtonSetting buttonSetting;
	
	/**
	 * The button list
	 */
	private ButtonList buttonList;
	
	/**
	 * The button refresh
	 */
	private ButtonRefresh buttonRefresh;
	
	/**
	 * The button Next page
	 */
	private ButtonNextPage buttonNextPage;
	
	/**
	 * The button Previous page
	 */
	private ButtonPreviousPage buttonPreviousPage;
	
	/**
	 * The button Scroll down
	 */
	private ButtonScrollDown buttonScrollDown;
	
	/**
	 * Button search
	 */
	private ButtonSearch buttonSearch;
	
	/**
	 * The button Scroll up
	 */
	private ButtonScrollUp buttonScrollUp;
	
	
	public ButtonEdit getButtonEdit() {
		return buttonEdit;
	}

	public ButtonSave getButtonSave() {
		return buttonSave;
	}

	public ButtonAddAEC getButtonAddAEC() {
		return buttonAddAEC;
	}

	public ButtonAddAttribute getButtonAddAttribute() {
		return buttonAddAttribute;
	}

	public ButtonArrowDown getButtonArrowDown() {
		return buttonArrowDown;
	}

	public ButtonArrowUp getButtonArrowUp() {
		return buttonArrowUp;
	}

	public ButtonDeleteComponent getButtonDeleteComponent() {
		return buttonDeleteComponent;
	}

	public ButtonDeleteAttribute getButtonDeleteAttribute() {
		return buttonDeleteAttribute;
	}

	public ButtonEscape getButtonEscape() {
		return buttonEscape;
	}

	public ButtonGenerate getButtonGenerate() {
		return buttonGenerate;
	}

	public ButtonSetting getButtonSetting() {
		return buttonSetting;
	}

	public ButtonList getButtonList() {
		return buttonList;
	}

	public ButtonRefresh getButtonRefresh() {
		return buttonRefresh;
	}

	public ButtonNextPage getButtonNextPage() {
		return buttonNextPage;
	}

	public ButtonPreviousPage getButtonPreviousPage() {
		return buttonPreviousPage;
	}

	public ButtonScrollDown getButtonScrollDown() {
		return buttonScrollDown;
	}

	public ButtonSearch getButtonSearch() {
		return buttonSearch;
	}

	public ButtonScrollUp getButtonScrollUp() {
		return buttonScrollUp;
	}

	public ButtonAddGroup getButtonAddGroup() {
		return buttonAddGroup;
	}

	public ButtonDeleteGroup getButtonDeleteGroup() {
		return buttonDeleteGroup;
	}

	public ButtonTakeGroupDown getButtonTakeGroupDown() {
		return buttonTakeGroupDown;
	}

	public ButtonTakeGroupUp getButtonTakeGroupUp() {
		return buttonTakeGroupUp;
	}
}