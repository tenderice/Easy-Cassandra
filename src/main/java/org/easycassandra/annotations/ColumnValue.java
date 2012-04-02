/*
 * Copyright 2012 Otávio Gonçalves de Santana (otaviojava)
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.easycassandra.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * annotations for  identify the column in the Family Column
 * The classes can be used are:
 * @see Boolean
 * @see java#util#Date
 * @see Double
 * @see Float
 * @see Integer
 * @see Long
 * @see String
 * 
 * If you want use Enums 
 * @see EnumeratedValue
  * @author otavio
 */
@Target (ElementType.FIELD)
@Retention (RetentionPolicy.RUNTIME)
public @interface ColumnValue {
    /**
     * The valeu for the column name
     * if this no used the value is the field's name
     * @return the name of the Column  
     */
       String name() default "";
    
    
}
