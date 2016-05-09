package org.aksw.mex.log4mex.core;

/**
 * Created by esteves on 26.06.15.
 */
public class ExampleVO implements IDataSetExample{

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public String getValue() {
        return _value;
    }

    public void setValue(String _value) {
        this._value = _value;
    }

    public long getDatasetColumn() {
        return _dsColumn;
    }

    public void setDatasetColumn(long _id) {
        this._dsColumn = _id;
    }

    public long getDatasetRow() {
        return _dsRow;
    }

    public void setDatasetRow(long _value) {
        this._dsRow = _value;
    }

    private String _id;
    private String _value;
    private long _dsColumn;
    private long _dsRow;

    public ExampleVO(){
    }

    public ExampleVO(String id, String value){
        this._id = id;
        this._value=value;
    }


}
