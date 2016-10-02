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
	Class which execute the command pattern on the thread
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.executor;

import java.util.HashMap;

import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.AddAEC;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.AddAttribute;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.ChangeAECattributePositionDown;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.ChangeAECattributePositionUp;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.ChangeAECpositionDown;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.ChangeAECpositionUp;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.ChangeAttributeGroup;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.ChangeColorBright;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.ChangeColorDark;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.DeleteAEC;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.DeleteAttribute;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.DisplayGenPathARXML;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.DisplayGenPathH;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.DisplayGenPathS00;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.DisplayGenerationConfig;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.DisplayList;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.DisplayNextPage;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.DisplayPreviousPage;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.EditAEC;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.Escape;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.Generate;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.ManageAECgroup;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.Refresh;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.Save;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.ScrollDown;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.ScrollUp;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.SearchAEC;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.SelectAEC;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.SelectAttribute;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.SelectNextAEC;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.SelectNextAttribute;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.SelectPreviousAEC;
import fr.autoliv.pp4g.erh.aecConfigurator.controller.executor.functionnality.SelectPreviousAttribute;

public class Executor {
	
    /**
     * The process that could be called on the raspberry
     */
    private HashMap<String, ICommand> commands;
    
    /**
     * The id of the command that will be executed
     */
    private String id_command;
    
    /**
     * The Thread use to execute the command
     */
    private ActionThread actionThread;
    
    /**
     * Constructor of the Executor
     */
    public Executor(){
	    // Create the object commands which will have all the process
	    this.commands = new HashMap<>();
	    
	    // Create the Thread where the command will be executed
	    this.actionThread=new ActionThread();
	    
        // the different command
	    this.commands.put(IDCommand.SAVE, new Save());
	    this.commands.put(IDCommand.ADD_AEC, new AddAEC());
	    this.commands.put(IDCommand.ADD_ATTRIBUTE, new AddAttribute());
	    this.commands.put(IDCommand.CHANGE_AEC_ATTRIBUTE_POSITION_DOWN, new ChangeAECattributePositionDown());
	    this.commands.put(IDCommand.CHANGE_AEC_ATTRIBUTE_POSITION_UP, new ChangeAECattributePositionUp());
	    this.commands.put(IDCommand.CHANGE_AEC_POSITION_DOWN, new ChangeAECpositionDown());
	    this.commands.put(IDCommand.CHANGE_AEC_POSITION_UP, new ChangeAECpositionUp());
	    this.commands.put(IDCommand.DELETE_ATTRIBUTE, new DeleteAttribute());
	    this.commands.put(IDCommand.DELETE_COMPONENT, new DeleteAEC());
	    this.commands.put(IDCommand.DISPLAY_LIST, new DisplayList());
	    this.commands.put(IDCommand.DISPLAY_NEXT_PAGE, new DisplayNextPage());
	    this.commands.put(IDCommand.DISPLAY_PREVIOUS_PAGE, new DisplayPreviousPage());
	    this.commands.put(IDCommand.EDIT_AEC, new EditAEC());
	    this.commands.put(IDCommand.ESCAPE, new Escape());
	    this.commands.put(IDCommand.GENERATE, new Generate());
	    this.commands.put(IDCommand.DISPLAY_GENERATION_CONFIG, new DisplayGenerationConfig());
	    this.commands.put(IDCommand.REFRESH, new Refresh());
	    this.commands.put(IDCommand.SCROLL_DOWN, new ScrollDown());
	    this.commands.put(IDCommand.SCROLL_UP, new ScrollUp());
	    this.commands.put(IDCommand.SEARCH_AEC, new SearchAEC());
	    this.commands.put(IDCommand.SELECT_NEXT_AEC, new SelectNextAEC());
	    this.commands.put(IDCommand.SELECT_NEXT_ATTRIBUTE, new SelectNextAttribute());
	    this.commands.put(IDCommand.SELECT_PREVIOUS_AEC, new SelectPreviousAEC());
	    this.commands.put(IDCommand.SELECT_PREVIOUS_ATTRIBUTE, new SelectPreviousAttribute());
	    this.commands.put(IDCommand.SELECT_AEC, new SelectAEC());
	    this.commands.put(IDCommand.CHANGE_COLOR_BRIGHT, new ChangeColorBright());
	    this.commands.put(IDCommand.CHANGE_COLOR_DARK, new ChangeColorDark());
	    this.commands.put(IDCommand.SELECT_ATTRIBUTE, new SelectAttribute());
	    this.commands.put(IDCommand.CHANGE_ATTRIBUTE_GROUP, new ChangeAttributeGroup());
	    this.commands.put(IDCommand.DISPLAY_GEN_PATH_ARXML, new DisplayGenPathARXML());
	    this.commands.put(IDCommand.DISPLAY_GEN_PATH_S00, new DisplayGenPathS00());
	    this.commands.put(IDCommand.DISPLAY_GEN_PATH_H, new DisplayGenPathH());
	    this.commands.put(IDCommand.DISPLAY_MANAGE_AEC_GROUP, new ManageAECgroup());
    }
    
    /**
     * Process called to execute the command from the package functionnality
     * @param id_command
     */
    synchronized public void executeCommand(String id_command){
    	if(
    		this.actionThread.getState()==Thread.State.NEW||
    		this.actionThread.getState()==Thread.State.TERMINATED
    	){
    		this.actionThread=new ActionThread();
    		this.id_command = id_command;
    		this.actionThread.start();
    	}
    }
    
    /**
     * Nested class of the Thread which will execute the different action
     */
    private class ActionThread extends Thread{
    	
    	@Override
    	public void run(){
    		commands.get(id_command).execute();
    	}
    }
}
