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
	The interface of the command pattern : use to add an AEC
 */

package fr.autoliv.pp4g.erh.aecConfigurator.controller.executor;

public interface ICommand {
	
	/**
	 * Process called to execute an action.
	 */
	void execute();
}
