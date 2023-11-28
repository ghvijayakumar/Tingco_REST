/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.jaxbUtil;

import se.info24.measurementTypes.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.UUID;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import org.hibernate.Session;
import se.info24.pojo.Device;
import se.info24.measurementjaxb.MeasurementDatas;
import se.info24.measurementjaxb.MeasurementTypeTranslation;
import se.info24.measurementjaxb.MeasurementTypes;
import se.info24.measurementjaxb.MsgStatus;
import se.info24.measurementjaxb.ObjectFactory;
import se.info24.measurementjaxb.TingcoMeasurementTypes;
import se.info24.pojo.MeasurementTypeTranslations;
import se.info24.pojo.MeasurementTypesInGroups;
import sun.reflect.generics.tree.Tree;

/**
 *
 * @author Hitha
 */
public class TingcoMeasurementXML {

    private TingcoMeasurementTypes tingcoMeasurement;
    ObjectFactory objectFactory;
    private MeasurementDAO dao = new MeasurementDAO();

    public TingcoMeasurementXML() {
        objectFactory = new ObjectFactory();
    }

    public TingcoMeasurementTypes buildTingcoMeasurementTemplate() throws DatatypeConfigurationException {
        tingcoMeasurement = objectFactory.createTingcoMeasurementTypes();
        tingcoMeasurement.setCreateRef("Info24");
        tingcoMeasurement.setMsgVer(new BigDecimal("1.0"));
        tingcoMeasurement.setRegionalUnits("Metric");
        tingcoMeasurement.setTimeZone("UTC");

        tingcoMeasurement.setMsgID(UUID.randomUUID().toString());
        tingcoMeasurement.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));

        MsgStatus msgStatus = new MsgStatus();
        msgStatus.setResponseCode(0);
        msgStatus.setResponseText("OK");

        tingcoMeasurement.setMsgStatus(msgStatus);
        return tingcoMeasurement;
    }

    public TingcoMeasurementTypes buildTingcoTypeTranslations(TingcoMeasurementTypes tingcoMeasurement, List<MeasurementTypeTranslations> meaurementTypeTranslationsList) {
        MeasurementTypes measurementTypes = new MeasurementTypes();
        int seqNo = 0;
        measurementTypes.setSeqNo(++seqNo);
        se.info24.measurementjaxb.MeasurementTypeTranslations mtts = new se.info24.measurementjaxb.MeasurementTypeTranslations();
        for (MeasurementTypeTranslations mtt : meaurementTypeTranslationsList) {
            MeasurementTypeTranslation mt = new MeasurementTypeTranslation();
            mt.setMeasurementTypeID(mtt.getId().getMeasurementTypeId());
            mt.setMeasurementTypeName(mtt.getMeasurementTypeName());
            if (mtt.getMeasurementTypes().getObjectUsageUnits() != null) {
                mt.setUsageUnit(mtt.getMeasurementTypes().getObjectUsageUnits().getUsageUnitName());
            }
            mtt.getMeasurementTypes().getObjectUsageUnits().getUsageUnitName();
            mt.setCountryID(mtt.getCountry().getCountryId());
            mtts.getMeasurementTypeTranslation().add(mt);
        }
        measurementTypes.getMeasurementTypeTranslations().add(mtts);
        tingcoMeasurement.setMeasurementTypes(measurementTypes);
        return tingcoMeasurement;
    }

    public TingcoMeasurementTypes buildTingcoMeasurementData(TingcoMeasurementTypes tingcoMeasurement, List<Object> measurementDataList, String groupId, Session session) {
        MeasurementDatas mds = new MeasurementDatas();
        int seqNo = 0;
        mds.setSeqNo(++seqNo);
        for (Iterator itr = measurementDataList.iterator(); itr.hasNext();) {
            Object[] row = (Object[]) itr.next();
            se.info24.measurementjaxb.MeasurementData mdata = new se.info24.measurementjaxb.MeasurementData();
            for (int i = 0; i < row.length; i++) {
                if (i % 2 == 0) {
                    mdata.getContent().add(objectFactory.createObjectID(row[i].toString()));
                    List<Device> deviceList = dao.getDeviceList(row[i], groupId, session);
                    if (!deviceList.isEmpty()) {
                        mdata.getContent().add(objectFactory.createDeviceName(deviceList.get(0).getDeviceName()));
                    }
                    mdata.getContent().add(objectFactory.createMeasurementValue(Float.valueOf(row[i + 1].toString())));
                }
            }
            mds.getMeasurementData().add(mdata);
        }
        tingcoMeasurement.setMeasurementDatas(mds);
        return tingcoMeasurement;
    }

    public TingcoMeasurementTypes buildTingcoMeasurementDataWithGroupBy(TingcoMeasurementTypes tingcoMeasurement, List<Object> measurementDataList, String groupId, Session session, String groupBy) {
        MeasurementDatas mds = new MeasurementDatas();
        int seqNo = 0;
        mds.setSeqNo(++seqNo);
        for (Iterator itr = measurementDataList.iterator(); itr.hasNext();) {
            Object[] row = (Object[]) itr.next();
            se.info24.measurementjaxb.MeasurementData mdata = new se.info24.measurementjaxb.MeasurementData();
            for (int i = 0; i < row.length; i++) {
                if (i % 2 == 0) {
                    if (row[i] != null) {
                        mdata.getContent().add(objectFactory.createMeasurementValue(Float.valueOf(row[i].toString())));
                    }
                    if (groupBy.equalsIgnoreCase("year")) {
                        if (row[i + 1] != null) {
                            mdata.getContent().add(objectFactory.createYear(Integer.parseInt(row[i + 1].toString())));
                        }
                    } else if (groupBy.equalsIgnoreCase("month")) {
                        if (row[i + 1] != null) {
                            mdata.getContent().add(objectFactory.createMonth(Integer.parseInt(row[i + 1].toString())));
                        }
                    } else if (groupBy.equalsIgnoreCase("day")) {
                        if (row[i + 1] != null) {
                            mdata.getContent().add(objectFactory.createYear(Integer.parseInt(row[i + 1].toString())));
                        }
                        if (row[i + 2] != null) {
                            mdata.getContent().add(objectFactory.createMonth(Integer.parseInt(row[i + 2].toString())));
                        }
                        if (row[i + 3] != null) {
                            mdata.getContent().add(objectFactory.createDay(Float.valueOf(row[i + 3].toString()).intValue()));
                        }
                        i += 3;
                    } else if (groupBy.equalsIgnoreCase("hour")) {
                        if (row[i + 1] != null) {
                            mdata.getContent().add(objectFactory.createHour(Integer.parseInt(row[i + 1].toString())));
                        }
                    }
                }
            }
            mds.getMeasurementData().add(mdata);
        }
        tingcoMeasurement.setMeasurementDatas(mds);
        return tingcoMeasurement;
    }

    public TingcoMeasurementTypes buildMeasurementTypesForGroup(TingcoMeasurementTypes tingcoMeasurement, List<MeasurementTypesInGroups> measurementTypesInGroupsList) {
        MeasurementTypes measurementTypes = new MeasurementTypes();
        int seqNo = 0;
        measurementTypes.setSeqNo(++seqNo);
        se.info24.measurementjaxb.MeasurementTypeTranslations mtts = new se.info24.measurementjaxb.MeasurementTypeTranslations();
        for (MeasurementTypesInGroups mtt : measurementTypesInGroupsList) {
            MeasurementTypeTranslation mt = new MeasurementTypeTranslation();
            mt.setMeasurementTypeID(mtt.getId().getMeasurementTypeId());
            mtts.getMeasurementTypeTranslation().add(mt);
        }
        measurementTypes.getMeasurementTypeTranslations().add(mtts);
        tingcoMeasurement.setMeasurementTypes(measurementTypes);
        return tingcoMeasurement;
    }

    public TingcoMeasurementTypes buildTingcoMeasurementDataWithGroupBydemo(TingcoMeasurementTypes tingcoMeasurement, Map<String, Double> data, String groupBy) {
        MeasurementDatas mds = new MeasurementDatas();
        int seqNo = 0;
        mds.setSeqNo(++seqNo);
        DecimalFormat df = new DecimalFormat("#.##");
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            se.info24.measurementjaxb.MeasurementData mdata = new se.info24.measurementjaxb.MeasurementData();
            mdata.getContent().add(objectFactory.createMeasurementValue(Float.parseFloat(df.format(entry.getValue()))));
            if (groupBy.equalsIgnoreCase("year")) {
                if (entry.getKey() != null) {
                    if (!entry.getKey().equalsIgnoreCase(null + "")) {
                        mdata.getContent().add(objectFactory.createYear(Integer.valueOf(entry.getKey())));
                    }

                }
            } else if (groupBy.equalsIgnoreCase("month")) {
                if (entry.getKey() != null) {
                    if (!entry.getKey().equalsIgnoreCase(null + "")) {
                        mdata.getContent().add(objectFactory.createMonth(Integer.valueOf(entry.getKey())));
                    }

                }

            } else if (groupBy.equalsIgnoreCase("day")) {
                if (entry.getKey() != null) {
                    String[] days = entry.getKey().split(",");
                    if (days[0] != null) {
                        if (!days[0].equalsIgnoreCase(null + "")) {
                            mdata.getContent().add(objectFactory.createYear(Integer.parseInt(days[0])));
                        }
                    }
                    if (days[1] != null) {
                        if (!days[1].equalsIgnoreCase(null + "")) {
                            mdata.getContent().add(objectFactory.createMonth(Integer.parseInt(days[1])));
                        }
                    }
                    if (days[2] != null) {
                        if (!days[2].equalsIgnoreCase(null + "")) {
                            mdata.getContent().add(objectFactory.createDay(Integer.parseInt(days[2])));
                        }
                    }
                }

            } else if (groupBy.equalsIgnoreCase("hour")) {
                if (entry.getKey() != null) {
                    if (!entry.getKey().equalsIgnoreCase(null + "")) {
                        mdata.getContent().add(objectFactory.createHour(Integer.valueOf(entry.getKey())));
                    }

                }
            }
            mds.getMeasurementData().add(mdata);
        }
        tingcoMeasurement.setMeasurementDatas(mds);
        return tingcoMeasurement;
    }
}
