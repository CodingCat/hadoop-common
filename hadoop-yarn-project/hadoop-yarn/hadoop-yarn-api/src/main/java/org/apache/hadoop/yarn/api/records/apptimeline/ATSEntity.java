/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.yarn.api.records.apptimeline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.hadoop.classification.InterfaceAudience.Public;
import org.apache.hadoop.classification.InterfaceStability.Unstable;

/**
 * <p>
 * The class that contains the the meta information of some conceptual entity of
 * an application and its related events. The entity can be an application, an
 * application attempt, a container or whatever the user-defined object.
 * </p>
 * 
 * <p>
 * Primary filters will be used to index the entities in
 * <code>ApplicationTimelineStore</code>, such that users should carefully
 * choose the information they want to store as the primary filters. The
 * remaining can be stored as other information.
 * </p>
 */
@XmlRootElement(name = "entity")
@XmlAccessorType(XmlAccessType.NONE)
@Public
@Unstable
public class ATSEntity {

  private String entityType;
  private String entityId;
  private long startTime;
  private List<ATSEvent> events = new ArrayList<ATSEvent>();
  private Map<String, List<Object>> relatedEntities =
      new HashMap<String, List<Object>>();
  private Map<String, Object> primaryFilters =
      new HashMap<String, Object>();
  private Map<String, Object> otherInfo =
      new HashMap<String, Object>();

  public ATSEntity() {

  }

  /**
   * Get the entity type
   * 
   * @return the entity type
   */
  @XmlElement(name = "entitytype")
  public String getEntityType() {
    return entityType;
  }

  /**
   * Set the entity type
   * 
   * @param entityType
   *          the entity type
   */
  public void setEntityType(String entityType) {
    this.entityType = entityType;
  }

  /**
   * Get the entity Id
   * 
   * @return the entity Id
   */
  @XmlElement(name = "entity")
  public String getEntityId() {
    return entityId;
  }

  /**
   * Set the entity Id
   * 
   * @param entityId
   *          the entity Id
   */
  public void setEntityId(String entityId) {
    this.entityId = entityId;
  }

  /**
   * Get the start time of the entity
   * 
   * @return the start time of the entity
   */
  @XmlElement(name = "starttime")
  public long getStartTime() {
    return startTime;
  }

  /**
   * Set the start time of the entity
   * 
   * @param startTime
   *          the start time of the entity
   */
  public void setStartTime(long startTime) {
    this.startTime = startTime;
  }

  /**
   * Get a list of events related to the entity
   * 
   * @return a list of events related to the entity
   */
  @XmlElement(name = "events")
  public List<ATSEvent> getEvents() {
    return events;
  }

  /**
   * Add a single event related to the entity to the existing event list
   * 
   * @param event
   *          a single event related to the entity
   */
  public void addEvent(ATSEvent event) {
    events.add(event);
  }

  /**
   * Add a list of events related to the entity to the existing event list
   * 
   * @param events
   *          a list of events related to the entity
   */
  public void addEvents(List<ATSEvent> events) {
    this.events.addAll(events);
  }

  /**
   * Set the event list to the given list of events related to the entity
   * 
   * @param events
   *          events a list of events related to the entity
   */
  public void setEvents(List<ATSEvent> events) {
    this.events = events;
  }

  /**
   * Get the related entities
   * 
   * @return the related entities
   */
  @XmlElement(name = "relatedentities")
  public Map<String, List<Object>> getRelatedEntities() {
    return relatedEntities;
  }

  /**
   * Add a list of entity of the same type to the existing related entity map
   * 
   * @param entityType
   *          the entity type
   * @param entityIds
   *          a list of entity Ids
   */
  public void addRelatedEntity(String entityType, List<Object> entityIds) {
    List<Object> thisRelatedEntity = relatedEntities.get(entityType);
    relatedEntities.put(entityType, entityIds);
    if (thisRelatedEntity == null) {
      relatedEntities.put(entityType, entityIds);
    } else {
      thisRelatedEntity.addAll(entityIds);
    }
  }

  /**
   * Add a map of related entities to the existing related entity map
   * 
   * @param relatedEntities
   *          a map of related entities
   */
  public void addRelatedEntities(
      Map<String, List<Object>> relatedEntities) {
    for (Map.Entry<String, List<Object>> relatedEntity : relatedEntities
        .entrySet()) {
      List<Object> thisRelatedEntity =
          this.relatedEntities.get(relatedEntity.getKey());
      if (thisRelatedEntity == null) {
        this.relatedEntities.put(
            relatedEntity.getKey(), relatedEntity.getValue());
      } else {
        thisRelatedEntity.addAll(relatedEntity.getValue());
      }
    }
  }

  /**
   * Set the related entity map to the given map of related entities
   * 
   * @param relatedEntities
   *          a map of related entities
   */
  public void setRelatedEntities(
      Map<String, List<Object>> relatedEntities) {
    this.relatedEntities = relatedEntities;
  }

  /**
   * Get the primary filters
   * 
   * @return the primary filters
   */
  @XmlElement(name = "primaryfilters")
  public Map<String, Object> getPrimaryFilters() {
    return primaryFilters;
  }

  /**
   * Add a single piece of primary filter to the existing primary filter map
   * 
   * @param key
   *          the primary filter key
   * @param value
   *          the primary filter value
   */
  public void addPrimaryFilter(String key, Object value) {
    primaryFilters.put(key, value);
  }

  /**
   * Add a map of primary filters to the existing primary filter map
   * 
   * @param primaryFilters
   *          a map of primary filters
   */
  public void addPrimaryFilters(Map<String, Object> primaryFilters) {
    this.primaryFilters.putAll(primaryFilters);
  }

  /**
   * Set the primary filter map to the given map of primary filters
   * 
   * @param primaryFilters
   *          a map of primary filters
   */
  public void setPrimaryFilters(Map<String, Object> primaryFilters) {
    this.primaryFilters = primaryFilters;
  }

  /**
   * Get the other information of the entity
   * 
   * @return the other information of the entity
   */
  @XmlElement(name = "otherinfo")
  public Map<String, Object> getOtherInfo() {
    return otherInfo;
  }

  /**
   * Add one piece of other information of the entity to the existing other info
   * map
   * 
   * @param key
   *          the other information key
   * @param value
   *          the other information value
   */
  public void addOtherInfo(String key, Object value) {
    this.otherInfo.put(key, value);
  }

  /**
   * Add a map of other information of the entity to the existing other info map
   * 
   * @param otherInfo
   *          a map of other information
   */
  public void addOtherInfo(Map<String, Object> otherInfo) {
    this.otherInfo.putAll(otherInfo);
  }

  /**
   * Set the other info map to the given map of other information
   * 
   * @param otherInfo
   *          a map of other information
   */
  public void setOtherInfo(Map<String, Object> otherInfo) {
    this.otherInfo = otherInfo;
  }

}
