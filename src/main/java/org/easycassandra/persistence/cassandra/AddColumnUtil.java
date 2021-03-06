/*
 * Copyright 2013 Otávio Gonçalves de Santana (otaviojava)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.easycassandra.persistence.cassandra;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;

import org.easycassandra.ListData;
import org.easycassandra.MapData;
import org.easycassandra.SetData;

/**
 * create a column to cassandra checking the annotation 
 * @author otaviojava
 */
enum AddColumnUtil {
    INSTANCE;

    public AddColumn factory(Field field){
        if (ColumnUtil.INTANCE.isEnumField(field)) {
            return new EnumAdd();
        }
        if(ColumnUtil.INTANCE.isList(field)){
            return new ListAdd();
        }
        if(ColumnUtil.INTANCE.isSet(field)){
            return new SetAdd();
        }
        if(ColumnUtil.INTANCE.isMap(field)){
            return new MapAdd();
        }
        if(ColumnUtil.INTANCE.isCustom(field)){
            return new CustomAdd();
        }
        
        return new DefaultAdd();
    }
    
class CustomAdd implements AddColumn{

    @Override
    public String addRow(Field field, RelationShipJavaCassandra javaCassandra) {
        String columnName = ColumnUtil.INTANCE.getColumnName(field);
        StringBuilder row=new StringBuilder();
        row.append(columnName).append(" ").append(javaCassandra.getPreferenceCQLType(ByteBuffer.class.getName())).append(",");
        return row.toString();
    }
    
}
    
class MapAdd implements AddColumn{

    @Override
    public String addRow(Field field, RelationShipJavaCassandra javaCassandra) {
        StringBuilder row=new StringBuilder();
        String columnName = ColumnUtil.INTANCE.getColumnName(field);
        MapData setData=field.getAnnotation(MapData.class);
        row.append(columnName).append(" map<").append(javaCassandra.getPreferenceCQLType(setData.classKey().getName())).append(",");
        row.append(javaCassandra.getPreferenceCQLType(setData.classValue().getName())).append(">,");
        return row.toString();
    }
    
}    
    
class SetAdd implements AddColumn{

    @Override
    public String addRow(Field field, RelationShipJavaCassandra javaCassandra) {
        StringBuilder row=new StringBuilder();
        String columnName = ColumnUtil.INTANCE.getColumnName(field);
        SetData setData=field.getAnnotation(SetData.class);
        row.append(columnName).append(" set<").append(javaCassandra.getPreferenceCQLType(setData.classData().getName())).append(">,");
        return row.toString();
    }
    
}
class ListAdd implements AddColumn{

    @Override
    public String addRow(Field field, RelationShipJavaCassandra javaCassandra) {
        StringBuilder row=new StringBuilder();
        String columnName = ColumnUtil.INTANCE.getColumnName(field);
        ListData listData=field.getAnnotation(ListData.class);
        row.append(columnName).append(" list<").append(javaCassandra.getPreferenceCQLType(listData.classData().getName())).append(">,");
        return row.toString();
    }
    
}
    
class EnumAdd implements AddColumn{

    @Override
    public String addRow(Field field,RelationShipJavaCassandra javaCassandra) {
        String columnName = ColumnUtil.INTANCE.getColumnName(field);
        StringBuilder row=new StringBuilder();
        row.append(columnName).append(" ").append(javaCassandra.getPreferenceCQLType(ColumnUtil.DEFAULT_ENUM_CLASS.getName())).append(",");
        return row.toString();
    }
    
}    

class DefaultAdd implements AddColumn{

    @Override
    public String addRow(Field field, RelationShipJavaCassandra javaCassandra) {
        String columnName = ColumnUtil.INTANCE.getColumnName(field);
        StringBuilder row=new StringBuilder();
        row.append(columnName).append(" ").append(javaCassandra.getPreferenceCQLType(field.getType().getName())).append(",");
        return row.toString();
    }
    
}
/**
 * The contract to create a column to cassandra checking the annotation
 * @author otaviojava
 *
 */
interface AddColumn{
    /**
     * create a column 
     * @param field
     * @param javaCassandra
     * @return
     */
    String addRow(Field field,RelationShipJavaCassandra javaCassandra); 
}
}
