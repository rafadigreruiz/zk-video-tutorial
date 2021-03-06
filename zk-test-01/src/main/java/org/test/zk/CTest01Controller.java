package org.test.zk;

import org.test.zk.dao.CPerson;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.ItemRenderer;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Selectbox;
import org.zkoss.zul.Window;


public class CTest01Controller extends SelectorComposer<Component> implements ItemRenderer <CPerson> {

    private static final long serialVersionUID = -258902408111073465L;
    
    @Wire
    Button buttonTest01;
    
    @Wire( "#buttonMiSuperButton" )
    Button buttonTest02;
    
    @Wire
    Selectbox selectbox01;
    
    @Wire
    Selectbox selectbox02;
    
    protected ListModelList<String> dataModel = new ListModelList<String>();
    
    protected ListModelList<CPerson> dataModelPerson = new ListModelList<CPerson>();
    
    @Wire
    Window windowTest01;
    
    @Listen( "onClick=#buttonTest01" )
    public void onClickButtonTest01(Event event) {
      
        windowTest01.setTitle( "Click button test 01" );
        
        buttonTest02.setLabel( "Funciona!" );
        
        dataModel.add( "1" );
        dataModel.add( "2" );
        dataModel.add( "3" );
        dataModel.add( "4" );
        dataModel.add( "5" );
        
        dataModelPerson.add(new CPerson( "12345678", "Rafael", "Di Gregorio" ) );
        dataModelPerson.add(new CPerson( "24321745", "Jose", "Romero" ) );
        dataModelPerson.add(new CPerson( "24321738", "Tomas", "Moreno" ) );
        
        selectbox02.setModel( dataModelPerson );
        selectbox02.setItemRenderer( this );
        
        selectbox01.setModel( dataModel );
        dataModel.addToSelection( "1" );
        selectbox01.setSelectedIndex( 0 );
        
    }
    
    @Listen( "onClick=#buttonMiSuperButton" )
    public void onClickButtonTest02(Event event) {
      
        buttonTest02.setLabel( "Evento!" );
        
    }
    
    @Listen( "onClick=#buttonTest03" )
    public void onClickButtonTest03(Event event) {
       
        
        windowTest01.doModal();
        
    }
    
    @Listen( "onSelect=#selectbox01" )
    public void onSelectselectbox01(Event event) {
      

        if ( selectbox01.getSelectedIndex() >= 0 ) {
            
            windowTest01.setTitle( dataModel.get( selectbox01.getSelectedIndex() ) );
            
        }
        
    }
    
    @Listen( "onSelect=#selectbox02" )
    public void onSelectselectbox02(Event event) {
      
        if ( selectbox02.getSelectedIndex() >= 0 ) {
            
            CPerson personSelected = dataModelPerson.get( selectbox02.getSelectedIndex() );
            
            windowTest01.setTitle(personSelected.getFirstName() + " " + personSelected.getLastName() );
            
        }
        
    }

    @Override
    public String render( Component arg0, CPerson arg1, int arg2 ) throws Exception {
        
        return arg1.getFirstName() + " " + arg1.getLastName();
        
    }
    
}
