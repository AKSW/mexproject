package org.aksw.mex.core;

import java.util.Date;

/**
 * Created by esteves on 26.06.15.
 */
public class ExecutionSetVO extends Execution {

    private String _startPosition;
    private String _endPosition;

    public ExecutionSetVO(String id){
        this._id = id;

    }

    public ExecutionSetVO(String id, PhaseVO phase){
        this._id = id;
        this._phase = phase;
    }

    public void setStartsAtPosition(String value){
        this._startPosition = value;
    }
    public void setEndsAtPosition(String value){
        this._endPosition = value;
    }
    public String getStartsAtPosition(){
        return this._startPosition;
    }
    public String getEndsAtPosition(){
        return this._endPosition;
    }

}
