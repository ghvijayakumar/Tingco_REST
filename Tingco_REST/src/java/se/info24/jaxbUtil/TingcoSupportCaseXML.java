/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.jaxbUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import org.hibernate.Session;
import se.info24.device.DeviceDAO;
import se.info24.group.GroupDAO;
import se.info24.pojo.CostCenters;
import se.info24.pojo.Device;
import se.info24.pojo.GroupTranslations;
import se.info24.pojo.GroupTranslationsId;
import se.info24.pojo.SupportCaseDeviceLinks;
import se.info24.pojo.SupportCaseImpacts;
import se.info24.pojo.SupportCasePriorities;
import se.info24.pojo.SupportCaseResolutionTemplates;
import se.info24.pojo.SupportCaseStatuses;
import se.info24.pojo.SupportCaseSubjectTemplates;
import se.info24.pojo.SupportCaseTypes;
import se.info24.pojo.SupportImpactTranslations;
import se.info24.pojo.SupportOrgVisibleToGroups;
import se.info24.pojo.SupportOrganizations;
import se.info24.pojo.SupportPriorityTranslations;
import se.info24.pojo.SupportStatusTranslations;
import se.info24.pojo.SupportTypeTranslations;
import se.info24.pojo.Users2;
import se.info24.restUtil.RestUtilDAO;
import se.info24.supportcase.SupportCasesDAO;
import se.info24.supportcasejaxb.GroupID;
import se.info24.supportcasejaxb.MsgStatus;
import se.info24.supportcasejaxb.ObjectFactory;
import se.info24.supportcasejaxb.SupportCase;
import se.info24.supportcasejaxb.SupportCases;
import se.info24.supportcasejaxb.SupportOrganizationsVisibleToGroups;
import se.info24.supportcasejaxb.TingcoSupportCase;

/**
 *
 * @author Ravikant
 */
public class TingcoSupportCaseXML {

    DeviceDAO deviceDAO = new DeviceDAO();
    GroupDAO groupDAO = new GroupDAO();
    SupportCasesDAO supportCasesDAO = new SupportCasesDAO();

    public TingcoSupportCase buildGetSupportCasesBySearchCriteriaTim(TingcoSupportCase tingcoSupport, List<se.info24.pojo.SupportCases> supportcasesList, int countryId, String timeZoneGMToffset, boolean isSupportCaseTimeReport, Session session, List<SupportCaseStatuses> supportCaseStatusesesList) throws DatatypeConfigurationException {
        SupportCases supportCases = new SupportCases();
        GregorianCalendar gc = new GregorianCalendar();
//        int seqNo = 0;
        for (se.info24.pojo.SupportCases sc : supportcasesList) {
            for (SupportCaseStatuses supportCaseStatuses : supportCaseStatusesesList) {
                if (sc.getSupportCaseStatuses().getSupportCaseStatusId().equalsIgnoreCase(supportCaseStatuses.getSupportCaseStatusId())) {
                    SupportCase supportCase = new SupportCase();
//            supportCase.setSeqNo(seqNo++);
                    supportCase.setSupportCaseID(sc.getSupportCaseId());
                    supportCase.setSupportCaseNumber(sc.getSupportCaseNumber());
                    supportCase.setSupportCaseSubject(sc.getSupportCaseSubject());
                    supportCase.setDurationActual(sc.getDurationActual());
                    gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, sc.getReportedDate()));
                    supportCase.setReportedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc) + "");

                    if (!isSupportCaseTimeReport) {
                        if (sc.getSupportCasePriorities() != null) {
                            SupportCasePriorities supportCasePrio = supportCasesDAO.getSupportCasePrioritiesByPriorityId(sc.getSupportCasePriorities().getSupportCasePriorityId(), session);
                            SupportPriorityTranslations supportPrioTrans = supportCasesDAO.getSupportPriorityTranslationsByIds(sc.getSupportCasePriorities().getSupportCasePriorityId(), countryId, session);
                            se.info24.supportcasejaxb.SupportCasePriorities scp = new se.info24.supportcasejaxb.SupportCasePriorities();
                            if (supportCasePrio != null) {
                                scp.setIconURL(supportCasePrio.getIconUrl());
                            }
                            se.info24.supportcasejaxb.SupportPriorityTranslations spt = new se.info24.supportcasejaxb.SupportPriorityTranslations();
                            spt.setSupportCasePriorityID(sc.getSupportCasePriorities().getSupportCasePriorityId());
                            spt.setSupportCasePriorityName(supportPrioTrans.getSupportCasePriorityName());
                            scp.getSupportPriorityTranslations().add(spt);
                            supportCase.getSupportCasePriorities().add(scp);
                        }

                        if (sc.getSupportCaseStatuses() != null) {
                            SupportStatusTranslations supportStatusTrans = supportCasesDAO.getSupportStatusTranslationsByIds(sc.getSupportCaseStatuses().getSupportCaseStatusId(), countryId, session);
                            se.info24.supportcasejaxb.SupportCaseStatuses scs = new se.info24.supportcasejaxb.SupportCaseStatuses();
                            se.info24.supportcasejaxb.SupportStatusTranslations sst = new se.info24.supportcasejaxb.SupportStatusTranslations();
                            sst.setSupportCaseStatusID(sc.getSupportCaseStatuses().getSupportCaseStatusId());
                            sst.setSupportCaseStatusName(supportStatusTrans.getSupportCaseStatusName());
                            scs.getSupportStatusTranslations().add(sst);
                            supportCase.getSupportCaseStatuses().add(scs);
                        }
                    }

                    if (sc.getGroups() != null) {
                        GroupTranslations groupTrans = groupDAO.getGroupTranslationsByIds(sc.getGroups().getGroupId(), countryId, session);
                        GroupID groupID = new GroupID();
                        groupID.setValue(sc.getGroups().getGroupId());
                        groupID.setGroupName(groupTrans.getGroupName());
                        supportCase.setGroupID(groupID);
                    }

                    List<SupportCaseDeviceLinks> scdl = supportCasesDAO.getSupportCaseDeviceLinks(sc.getSupportCaseId(), session);
                    if (!scdl.isEmpty()) {
                        Device device = deviceDAO.getDeviceById(scdl.get(0).getDevice().getDeviceId(), session);
                        supportCase.setDeviceID(scdl.get(0).getDevice().getDeviceId());
                        supportCase.setDeviceName(device.getDeviceName());
                    }

                    if (sc.getCostCenterId() != null) {
                        CostCenters cc = groupDAO.getCostCenterById(sc.getCostCenterId(), session);
                        se.info24.supportcasejaxb.CostCenters costCenters = new se.info24.supportcasejaxb.CostCenters();
                        costCenters.setCostCenterID(sc.getCostCenterId());
                        costCenters.setCostCenterName(cc.getCostCenterName());
                        supportCase.getCostCenters().add(costCenters);
                    }
                    supportCases.getSupportCase().add(supportCase);
                }
            }
        }
        tingcoSupport.setSupportCases(supportCases);
        return tingcoSupport;
    }

    public TingcoSupportCase buildGetSupportCasesBySearchCriteria(TingcoSupportCase tingcoSupport, List<se.info24.pojo.SupportCases> supportcasesList, int countryId, String timeZoneGMToffset, boolean isSupportCaseTimeReport, Session session) throws DatatypeConfigurationException {
        SupportCases supportCases = new SupportCases();
        GregorianCalendar gc = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        int seqNo = 0;
        for (se.info24.pojo.SupportCases sc : supportcasesList) {
            SupportCase supportCase = new SupportCase();
//            supportCase.setSeqNo(seqNo++);
            supportCase.setSupportCaseID(sc.getSupportCaseId());
            System.out.println("sc is " + sc.getSupportCaseId() + " " + sc.getSupportCaseNumber() + " " + sc.getSupportCaseSubject() + " sc " + sc.getDurationActual());
            supportCase.setSupportCaseNumber(sc.getSupportCaseNumber());
            supportCase.setSupportCaseSubject(sc.getSupportCaseSubject());
            supportCase.setDurationActual(sc.getDurationActual());
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, sc.getReportedDate()));
            supportCase.setReportedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc) + "");
            supportCase.setReportedDateForTIM(df.format(gc.getTime()));

            if (!isSupportCaseTimeReport) {
                if (sc.getSupportCasePriorities() != null) {
                    SupportCasePriorities supportCasePrio = supportCasesDAO.getSupportCasePrioritiesByPriorityId(sc.getSupportCasePriorities().getSupportCasePriorityId(), session);
                    SupportPriorityTranslations supportPrioTrans = supportCasesDAO.getSupportPriorityTranslationsByIds(sc.getSupportCasePriorities().getSupportCasePriorityId(), countryId, session);
                    se.info24.supportcasejaxb.SupportCasePriorities scp = new se.info24.supportcasejaxb.SupportCasePriorities();
                    if (supportCasePrio != null) {
                        scp.setIconURL(supportCasePrio.getIconUrl());
                    }
                    se.info24.supportcasejaxb.SupportPriorityTranslations spt = new se.info24.supportcasejaxb.SupportPriorityTranslations();
                    spt.setSupportCasePriorityID(sc.getSupportCasePriorities().getSupportCasePriorityId());
                    spt.setSupportCasePriorityName(supportPrioTrans.getSupportCasePriorityName());
                    scp.getSupportPriorityTranslations().add(spt);
                    supportCase.getSupportCasePriorities().add(scp);
                }

                if (sc.getSupportCaseStatuses() != null) {
                    SupportStatusTranslations supportStatusTrans = supportCasesDAO.getSupportStatusTranslationsByIds(sc.getSupportCaseStatuses().getSupportCaseStatusId(), countryId, session);
                    se.info24.supportcasejaxb.SupportCaseStatuses scs = new se.info24.supportcasejaxb.SupportCaseStatuses();
                    se.info24.supportcasejaxb.SupportStatusTranslations sst = new se.info24.supportcasejaxb.SupportStatusTranslations();
                    sst.setSupportCaseStatusID(sc.getSupportCaseStatuses().getSupportCaseStatusId());
                    sst.setSupportCaseStatusName(supportStatusTrans.getSupportCaseStatusName());
                    scs.getSupportStatusTranslations().add(sst);
                    supportCase.getSupportCaseStatuses().add(scs);
                }
            }

            if (sc.getGroups() != null) {
                GroupTranslations groupTrans = groupDAO.getGroupTranslationsByIds(sc.getGroups().getGroupId(), countryId, session);
                GroupID groupID = new GroupID();
                groupID.setValue(sc.getGroups().getGroupId());
                groupID.setGroupName(groupTrans.getGroupName());
                supportCase.setGroupID(groupID);
            }

            List<SupportCaseDeviceLinks> scdl = supportCasesDAO.getSupportCaseDeviceLinks(sc.getSupportCaseId(), session);
            if (!scdl.isEmpty()) {
                Device device = deviceDAO.getDeviceById(scdl.get(0).getDevice().getDeviceId(), session);
                supportCase.setDeviceID(scdl.get(0).getDevice().getDeviceId());
                supportCase.setDeviceName(device.getDeviceName());
            }

            if (sc.getCostCenterId() != null) {
                CostCenters cc = groupDAO.getCostCenterById(sc.getCostCenterId(), session);
                se.info24.supportcasejaxb.CostCenters costCenters = new se.info24.supportcasejaxb.CostCenters();
                costCenters.setCostCenterID(sc.getCostCenterId());
                costCenters.setCostCenterName(cc.getCostCenterName());
                supportCase.getCostCenters().add(costCenters);
            }

            supportCases.getSupportCase().add(supportCase);
        }

        tingcoSupport.setSupportCases(supportCases);
        return tingcoSupport;
    }

    public TingcoSupportCase buildGetSupportCasesDetails(TingcoSupportCase tingcoSupport, se.info24.pojo.SupportCases ss, SupportTypeTranslations stt, SupportImpactTranslations sit, SupportPriorityTranslations spt, SupportStatusTranslations sst, GroupTranslations groupTrans, Users2 users2Reported, Device device, SupportOrganizations so, Users2 users2Assigned, CostCenters cc, String timeZoneGMToffset) throws DatatypeConfigurationException {
        GregorianCalendar gc = new GregorianCalendar();
        SupportCases supportCases = new SupportCases();
        SupportCase supportCase = new SupportCase();
        supportCase.setSupportCaseID(ss.getSupportCaseId());
        supportCase.setSupportCaseNumber(ss.getSupportCaseNumber());
        supportCase.setSupportCaseSubject(ss.getSupportCaseSubject());

        if (stt != null) {
            se.info24.supportcasejaxb.SupportCaseTypes supportCaseTypes = new se.info24.supportcasejaxb.SupportCaseTypes();
            se.info24.supportcasejaxb.SupportTypeTranslations supportTypeTrans = new se.info24.supportcasejaxb.SupportTypeTranslations();
            supportTypeTrans.setSupportCaseTypeID(ss.getSupportCaseTypes().getSupportCaseTypeId());
            supportTypeTrans.setSupportCaseTypeName(stt.getSupportCaseTypeName());
            supportCaseTypes.getSupportTypeTranslations().add(supportTypeTrans);
            supportCase.getSupportCaseTypes().add(supportCaseTypes);
        }

        if (sit != null) {
            se.info24.supportcasejaxb.SupportCaseImpacts supportCaseImpacts = new se.info24.supportcasejaxb.SupportCaseImpacts();
            se.info24.supportcasejaxb.SupportImpactTranslations supportImpactTrans = new se.info24.supportcasejaxb.SupportImpactTranslations();
            supportImpactTrans.setSupportCaseImpactID(ss.getSupportCaseImpacts().getSupportCaseImpactId());
            supportImpactTrans.setSupportCaseImpactName(sit.getSupportCaseImpactName());
            supportCaseImpacts.getSupportImpactTranslations().add(supportImpactTrans);
            supportCase.getSupportCaseImpacts().add(supportCaseImpacts);
        }

        if (spt != null) {
            se.info24.supportcasejaxb.SupportCasePriorities supportCasePriorities = new se.info24.supportcasejaxb.SupportCasePriorities();
            se.info24.supportcasejaxb.SupportPriorityTranslations supportPrioTrans = new se.info24.supportcasejaxb.SupportPriorityTranslations();
            supportPrioTrans.setSupportCasePriorityID(ss.getSupportCasePriorities().getSupportCasePriorityId());
            supportPrioTrans.setSupportCasePriorityName(spt.getSupportCasePriorityName());
            supportCasePriorities.getSupportPriorityTranslations().add(supportPrioTrans);
            supportCase.getSupportCasePriorities().add(supportCasePriorities);
        }

        if (sst != null) {
            se.info24.supportcasejaxb.SupportCaseStatuses supportCaseStatuses = new se.info24.supportcasejaxb.SupportCaseStatuses();
            se.info24.supportcasejaxb.SupportStatusTranslations supportStatusTrans = new se.info24.supportcasejaxb.SupportStatusTranslations();
            supportStatusTrans.setSupportCaseStatusID(ss.getSupportCaseStatuses().getSupportCaseStatusId());
            supportStatusTrans.setSupportCaseStatusName(sst.getSupportCaseStatusName());
            supportCaseStatuses.getSupportStatusTranslations().add(supportStatusTrans);
            supportCase.getSupportCaseStatuses().add(supportCaseStatuses);
        }

        supportCase.setSupportCaseDescription(ss.getSupportCaseDescription());

        if (ss.getReproduceSteps() != null) {
            supportCase.setReproduceSteps(ss.getReproduceSteps());
        }
        if (ss.getResolutionText() != null) {
            supportCase.setResolutionText(ss.getResolutionText());
        }

        SimpleDateFormat lFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (ss.getReportedDate() != null) {
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, ss.getReportedDate()));
            supportCase.setReportedDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc) + "");
            supportCase.setReportedDateForTIM(lFormat.format(gc.getTime()));
        }
        if (ss.getDueDate() != null) {
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, ss.getDueDate()));
            supportCase.setDueDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc) + "");
            supportCase.setDueDateTCMV3(lFormat.format(gc.getTime()));
        }

        if (ss.getStartDate() != null) {
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, ss.getStartDate()));
            supportCase.setStartDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc) + "");
            supportCase.setStartDateTCMv3(lFormat.format(gc.getTime()));
        }

        if (ss.getCompletionDate() != null) {
            gc.setTime(RestUtilDAO.convertGMTtoUserLocalTime(timeZoneGMToffset, ss.getCompletionDate()));
            supportCase.setCompletionDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(gc) + "");
            supportCase.setCompletionDateTCMV3(lFormat.format(gc.getTime()));
        }

        /* Below is Impacted Organization*/
        GroupID groupID = new GroupID();
        groupID.setValue(ss.getGroups().getGroupId());
        groupID.setGroupName(groupTrans.getGroupName());
        supportCase.setGroupID(groupID);

        if (users2Reported != null) {
            supportCase.setReportedByUserID(users2Reported.getUserId());
            supportCase.setReportedByUserFirstName(users2Reported.getFirstName());
            supportCase.setReportedByUserLastName(users2Reported.getLastName());
        }

        if (device != null) {
            supportCase.setDeviceID(device.getDeviceId());
            supportCase.setDeviceName(device.getDeviceName());
        }

        /* Below is the Assign to Support Organization*/
        if (so != null) {
            se.info24.supportcasejaxb.SupportOrganizations supportOrg = new se.info24.supportcasejaxb.SupportOrganizations();
            supportOrg.setSupportOrganizationID(ss.getSupportOrganizations().getSupportOrganizationId());
            supportOrg.setOrganizationName(so.getOrganizationName());
            supportCase.getSupportOrganizations().add(supportOrg);
        }

        if (users2Assigned != null) {
            supportCase.setAssignedToUserID(users2Assigned.getUserId());
            supportCase.setAssignedToUserFirstName(users2Assigned.getFirstName());
            supportCase.setAssignedToUserLastName(users2Assigned.getLastName());
        }



        if (ss.getDurationEstimated() != null) {
            supportCase.setDurationEstimated(new BigInteger(String.valueOf(ss.getDurationEstimated())));
        }
        if (ss.getDurationActual() != null) {
            supportCase.setDurationActual(ss.getDurationActual());
        }

        if (cc != null) {
            se.info24.supportcasejaxb.CostCenters costCenters = new se.info24.supportcasejaxb.CostCenters();
            costCenters.setCostCenterID(cc.getCostCenterId());
            costCenters.setCostCenterName(cc.getCostCenterName());
            costCenters.setCostCenterNumber(cc.getCostCenterNumber());
            supportCase.getCostCenters().add(costCenters);
        }

        if (ss.getReportedByEmail() != null) {
            supportCase.setReportedByEmail(ss.getReportedByEmail());
        }
        if (ss.getReportedByPhone() != null) {
            supportCase.setReportedByPhone(ss.getReportedByPhone());
        }

        supportCases.getSupportCase().add(supportCase);
        tingcoSupport.setSupportCases(supportCases);
        return tingcoSupport;
    }

    public TingcoSupportCase buildGroupsForSupportOrgVisibleGroups(TingcoSupportCase tingcoSupport, List<GroupTranslations> groupTransList) {
        SupportCases supportCases = new SupportCases();
        SupportCase supportCase = new SupportCase();
        se.info24.supportcasejaxb.SupportOrganizations supportOrganizations = new se.info24.supportcasejaxb.SupportOrganizations();
        SupportOrganizationsVisibleToGroups supportOrgVisibleToGroups = new SupportOrganizationsVisibleToGroups();
        for (GroupTranslations gt : groupTransList) {
            GroupID groupId = new GroupID();
            groupId.setGroupName(gt.getGroupName());
            groupId.setValue(gt.getId().getGroupId());
            supportOrgVisibleToGroups.getGroupID().add(groupId);
        }
        supportOrganizations.setSupportOrganizationsVisibleToGroups(supportOrgVisibleToGroups);
        supportCase.getSupportOrganizations().add(supportOrganizations);
        supportCases.getSupportCase().add(supportCase);
        tingcoSupport.setSupportCases(supportCases);
        return tingcoSupport;
    }

    public TingcoSupportCase buildTingcoSupportCasesTemplate() throws DatatypeConfigurationException {
        TingcoSupportCase containersXML;
        ObjectFactory objectFactory = new ObjectFactory();
        containersXML = objectFactory.createTingcoSupportCase();
        containersXML.setCreateRef("Info24");
        containersXML.setMsgVer(new BigDecimal("1.0"));
        containersXML.setRegionalUnits("Metric");
        containersXML.setTimeZone("UTC");
        containersXML.setMsgID(UUID.randomUUID().toString());
        containersXML.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));
        MsgStatus msgStatus = new MsgStatus();
        msgStatus.setResponseCode(0);
        msgStatus.setResponseText("OK");
        containersXML.setMsgStatus(msgStatus);
        return containersXML;
    }

    public TingcoSupportCase buildGetSubjectTemplates(TingcoSupportCase tingcoSupport, List<SupportCaseSubjectTemplates> scstList) {
        se.info24.supportcasejaxb.SupportCases supportCases = new SupportCases();
        se.info24.supportcasejaxb.SupportCase sc = new SupportCase();
        int seq = 0;
        se.info24.supportcasejaxb.SupportCaseSubjectTemplates scst = null;
        for (SupportCaseSubjectTemplates supportCaseSubjectTemplates : scstList) {
            scst = new se.info24.supportcasejaxb.SupportCaseSubjectTemplates();
            scst.setSeqNo(seq++);
            scst.setSupportCaseSubjectID(supportCaseSubjectTemplates.getSupportCaseSubjectId());
            scst.setSupportCaseSubject(supportCaseSubjectTemplates.getSupportCaseSubject());
            sc.getSupportCaseSubjectTemplates().add(scst);
        }
        supportCases.getSupportCase().add(sc);
        tingcoSupport.setSupportCases(supportCases);
        return tingcoSupport;
    }

    public TingcoSupportCase buildGetSupportCaseTypes(TingcoSupportCase tingcoSupport, List<SupportTypeTranslations> sttList, List<SupportCaseTypes> sctList) {
        se.info24.supportcasejaxb.SupportCases supportCases = new SupportCases();
        se.info24.supportcasejaxb.SupportCase sc = new SupportCase();
        int seq = 0;
        se.info24.supportcasejaxb.SupportCaseSubjectTemplates scst = null;
        for (SupportCaseTypes supportCaseTypes : sctList) {
            se.info24.supportcasejaxb.SupportCaseTypes sct = new se.info24.supportcasejaxb.SupportCaseTypes();
            sct.setSeqNo(seq++);
            sct.getSupportCaseTypeID().add(supportCaseTypes.getSupportCaseTypeId());
            if (supportCaseTypes.getIconUrl() != null) {
                sct.setIconURL(supportCaseTypes.getIconUrl());
            }
            for (SupportTypeTranslations supportTypeTranslations : sttList) {
                if (supportTypeTranslations.getId().getSupportCaseTypeId().equalsIgnoreCase(supportCaseTypes.getSupportCaseTypeId())) {
                    se.info24.supportcasejaxb.SupportTypeTranslations stt = new se.info24.supportcasejaxb.SupportTypeTranslations();
                    stt.setSupportCaseTypeName(supportTypeTranslations.getSupportCaseTypeName());
                    sct.getSupportTypeTranslations().add(stt);
                }
            }
            sc.getSupportCaseTypes().add(sct);
        }

        supportCases.getSupportCase().add(sc);
        tingcoSupport.setSupportCases(supportCases);
        return tingcoSupport;
    }

    public TingcoSupportCase buildGetSupportCaseImpacts(TingcoSupportCase tingcoSupport, List<SupportImpactTranslations> sitList, List<SupportCaseImpacts> sctList) {
        se.info24.supportcasejaxb.SupportCases supportCases = new SupportCases();
        se.info24.supportcasejaxb.SupportCase sc = new SupportCase();
        int seq = 0;
        se.info24.supportcasejaxb.SupportCaseSubjectTemplates scst = null;
        for (SupportCaseImpacts supportCaseImpacts : sctList) {
            se.info24.supportcasejaxb.SupportCaseImpacts sci = new se.info24.supportcasejaxb.SupportCaseImpacts();
            sci.setSeqNo(seq++);
            sci.setSupportCaseImpactID(supportCaseImpacts.getSupportCaseImpactId());
            if (supportCaseImpacts.getIconUrl() != null) {
                sci.setIconURL(supportCaseImpacts.getIconUrl());

            }
            for (SupportImpactTranslations supportImpactTranslations : sitList) {
                if (supportImpactTranslations.getId().getSupportCaseImpactId().equalsIgnoreCase(supportCaseImpacts.getSupportCaseImpactId())) {
                    se.info24.supportcasejaxb.SupportImpactTranslations sit = new se.info24.supportcasejaxb.SupportImpactTranslations();
                    sit.setSupportCaseImpactName(supportImpactTranslations.getSupportCaseImpactName());
                    sci.getSupportImpactTranslations().add(sit);
                }
            }
            sc.getSupportCaseImpacts().add(sci);
        }

        supportCases.getSupportCase().add(sc);
        tingcoSupport.setSupportCases(supportCases);
        return tingcoSupport;
    }

    public TingcoSupportCase buildGetSupportCasePriorities(TingcoSupportCase tingcoSupport, List<SupportPriorityTranslations> sptList, List<SupportCasePriorities> scpList) {
        se.info24.supportcasejaxb.SupportCases supportCases = new SupportCases();
        se.info24.supportcasejaxb.SupportCase sc = new SupportCase();
        int seq = 0;
        for (SupportCasePriorities supportCasePriorities : scpList) {
            se.info24.supportcasejaxb.SupportCasePriorities scp = new se.info24.supportcasejaxb.SupportCasePriorities();
            scp.setSeqNo(seq++);
            scp.getSupportCasePriorityID().add(supportCasePriorities.getSupportCasePriorityId());
            if (supportCasePriorities.getIconUrl() != null) {
                scp.setIconURL(supportCasePriorities.getIconUrl());

            }
            for (SupportPriorityTranslations supportPriorityTranslations : sptList) {
                if (supportPriorityTranslations.getId().getSupportCasePriorityId().equalsIgnoreCase(supportCasePriorities.getSupportCasePriorityId())) {
                    se.info24.supportcasejaxb.SupportPriorityTranslations spt = new se.info24.supportcasejaxb.SupportPriorityTranslations();
                    spt.setSupportCasePriorityName(supportPriorityTranslations.getSupportCasePriorityName());
                    scp.getSupportPriorityTranslations().add(spt);
                }
            }
            sc.getSupportCasePriorities().add(scp);
        }

        supportCases.getSupportCase().add(sc);
        tingcoSupport.setSupportCases(supportCases);
        return tingcoSupport;
    }

    public TingcoSupportCase buildGetSupportCaseStatusesTim(TingcoSupportCase tingcoSupport, List<SupportStatusTranslations> sstList, List<SupportCaseStatuses> scsList) {
        se.info24.supportcasejaxb.SupportCases supportCases = new SupportCases();
        se.info24.supportcasejaxb.SupportCase sc = new SupportCase();
        int seq = 0;

        for (SupportStatusTranslations supportStatusTranslations : sstList) {
            for (SupportCaseStatuses supportCaseStatuses : scsList) {
                if (supportStatusTranslations.getId().getSupportCaseStatusId().equalsIgnoreCase(supportCaseStatuses.getSupportCaseStatusId())) {
                    se.info24.supportcasejaxb.SupportCaseStatuses scs = new se.info24.supportcasejaxb.SupportCaseStatuses();
                    scs.setSeqNo(seq++);
                    scs.getSupportCaseStatusID().add(supportCaseStatuses.getSupportCaseStatusId());
                    if (supportCaseStatuses.getIconUrl() != null) {
                        scs.setIconURL(supportCaseStatuses.getIconUrl());
                    }
                    se.info24.supportcasejaxb.SupportStatusTranslations sst = new se.info24.supportcasejaxb.SupportStatusTranslations();
                    sst.setSupportCaseStatusName(supportStatusTranslations.getSupportCaseStatusName());
                    scs.getSupportStatusTranslations().add(sst);
                    sc.getSupportCaseStatuses().add(scs);
                }
            }
        }


        supportCases.getSupportCase().add(sc);
        tingcoSupport.setSupportCases(supportCases);
        return tingcoSupport;
    }

    public TingcoSupportCase buildGetSupportCaseStatuses(TingcoSupportCase tingcoSupport, List<SupportStatusTranslations> sstList, List<SupportCaseStatuses> scsList) {
        se.info24.supportcasejaxb.SupportCases supportCases = new SupportCases();
        se.info24.supportcasejaxb.SupportCase sc = new SupportCase();
        int seq = 0;
        for (SupportCaseStatuses supportCaseStatuses : scsList) {
            se.info24.supportcasejaxb.SupportCaseStatuses scs = new se.info24.supportcasejaxb.SupportCaseStatuses();
            scs.setSeqNo(seq++);
            scs.getSupportCaseStatusID().add(supportCaseStatuses.getSupportCaseStatusId());
            if (supportCaseStatuses.getIconUrl() != null) {
                scs.setIconURL(supportCaseStatuses.getIconUrl());
            }
            for (SupportStatusTranslations supportStatusTranslations : sstList) {
                if (supportStatusTranslations.getId().getSupportCaseStatusId().equalsIgnoreCase(supportCaseStatuses.getSupportCaseStatusId())) {
                    se.info24.supportcasejaxb.SupportStatusTranslations sst = new se.info24.supportcasejaxb.SupportStatusTranslations();
                    sst.setSupportCaseStatusName(supportStatusTranslations.getSupportCaseStatusName());
                    scs.getSupportStatusTranslations().add(sst);
                }
            }
            sc.getSupportCaseStatuses().add(scs);
        }
        supportCases.getSupportCase().add(sc);
        tingcoSupport.setSupportCases(supportCases);
        return tingcoSupport;
    }

    public TingcoSupportCase buildGetSupportCaseResolutionTemplates(TingcoSupportCase tingcoSupport, List<SupportCaseResolutionTemplates> scrtList) {
        se.info24.supportcasejaxb.SupportCases supportCases = new SupportCases();
        se.info24.supportcasejaxb.SupportCase sc = new SupportCase();
        int seq = 0;
        for (SupportCaseResolutionTemplates supportCaseResolutionTemplates : scrtList) {
            se.info24.supportcasejaxb.SupportCaseResolutionTemplates scrt = new se.info24.supportcasejaxb.SupportCaseResolutionTemplates();
            if (supportCaseResolutionTemplates.getResolutionTemplateId() != null) {
                scrt.setResolutionTemplateID(supportCaseResolutionTemplates.getResolutionTemplateId());
            }
            if (supportCaseResolutionTemplates.getResolutionSubject() != null) {
                scrt.setResolutionSubject(supportCaseResolutionTemplates.getResolutionSubject());
            }
            if (supportCaseResolutionTemplates.getResolutionText() != null) {
                scrt.setResolutionText(supportCaseResolutionTemplates.getResolutionText());
            }
            sc.getSupportCaseResolutionTemplates().add(scrt);
        }
        supportCases.getSupportCase().add(sc);
        tingcoSupport.setSupportCases(supportCases);
        return tingcoSupport;
    }

    public TingcoSupportCase buildGetSupportOrganizations(TingcoSupportCase tingcoSupport, List<SupportOrganizations> soList, Session session) {
        se.info24.supportcasejaxb.SupportCases supportCases = new SupportCases();
        se.info24.supportcasejaxb.SupportCase sc = new SupportCase();
        for (SupportOrganizations supportOrganizations : soList) {
            se.info24.supportcasejaxb.SupportOrganizations so = new se.info24.supportcasejaxb.SupportOrganizations();
            so.setSupportOrganizationID(supportOrganizations.getSupportOrganizationId());
            so.setOrganizationName(supportOrganizations.getOrganizationName());
            sc.getSupportOrganizations().add(so);
        }
        supportCases.getSupportCase().add(sc);
        tingcoSupport.setSupportCases(supportCases);
        return tingcoSupport;
    }

    public TingcoSupportCase buildGetSupportOrganizationsByGroupId(TingcoSupportCase tingcoSupport, List<SupportOrganizations> soList, int countryId, Session session) {
        se.info24.supportcasejaxb.SupportCases supportCases = new SupportCases();
        se.info24.supportcasejaxb.SupportCase sc = new SupportCase();
        for (SupportOrganizations supportOrganizations : soList) {
            se.info24.supportcasejaxb.SupportOrganizations so = new se.info24.supportcasejaxb.SupportOrganizations();
            so.setSupportOrganizationID(supportOrganizations.getSupportOrganizationId());
            GroupTranslations gt = (GroupTranslations) session.get(GroupTranslations.class, new GroupTranslationsId(supportOrganizations.getManagedByGroupId(), countryId));
            so.setOrganizationName(supportOrganizations.getOrganizationName() + " (" + gt.getGroupName() + ") ");
            sc.getSupportOrganizations().add(so);
        }
        supportCases.getSupportCase().add(sc);
        tingcoSupport.setSupportCases(supportCases);
        return tingcoSupport;
    }

    public TingcoSupportCase buildGetSupportCaseSubjectTemplateDetails(TingcoSupportCase tingcoSupport, SupportCaseSubjectTemplates scst, Session session) {
        se.info24.supportcasejaxb.SupportCases supportCases = new SupportCases();
        se.info24.supportcasejaxb.SupportCase sc = new SupportCase();
        int seq = 0;
        if (scst.getSupportCaseTypeId() != null) {
            sc.setSupportCaseTypeID(scst.getSupportCaseTypeId());
        }
        if (scst.getSupportCaseImpactId() != null) {
            sc.setSupportCaseImpactID(scst.getSupportCaseImpactId());
        }
        if (scst.getSupportCasePriorityId() != null) {
            sc.setSupportCasePriorityID(scst.getSupportCasePriorityId());
        }
        if (scst.getSupportCaseStatusId() != null) {
            sc.setSupportCaseStatusID(scst.getSupportCaseStatusId());
        }

        se.info24.supportcasejaxb.SupportCaseSubjectTemplates scstJaxb = new se.info24.supportcasejaxb.SupportCaseSubjectTemplates();
        if (scst.getSupportCaseSubject() != null) {
            scstJaxb.setSupportCaseSubject(scst.getSupportCaseSubject());
        }
        if (scst.getReproduceSteps() != null) {
            scstJaxb.setReproduceSteps(scst.getReproduceSteps());
        }
        if (scst.getResolutionText() != null) {
            scstJaxb.setResolutionText(scst.getResolutionText());
        }
        if (scst.getCostCenterId() != null) {
            scstJaxb.setCostCenterID(scst.getCostCenterId());
            CostCenters costCenters = (CostCenters) session.get(CostCenters.class, scst.getCostCenterId());
            if (costCenters != null) {
                scstJaxb.setCostCenterName(costCenters.getCostCenterName());
            }
        }
        sc.getSupportCaseSubjectTemplates().add(scstJaxb);
        supportCases.getSupportCase().add(sc);
        tingcoSupport.setSupportCases(supportCases);
        return tingcoSupport;

    }

    public TingcoSupportCase buildGetSupportOrganizationList(TingcoSupportCase tingcoSupport, List<SupportOrganizations> supportOrganizationses, List<GroupTranslations> gtList) {
        SupportCases supportCases = new SupportCases();
        SupportCase supportCase = new SupportCase();
        for (SupportOrganizations supportOrganizations : supportOrganizationses) {
            for (GroupTranslations groupTranslations : gtList) {
                if (groupTranslations.getId().getGroupId().equalsIgnoreCase(supportOrganizations.getManagedByGroupId())) {
                    se.info24.supportcasejaxb.SupportOrganizations supportOrg = new se.info24.supportcasejaxb.SupportOrganizations();
                    supportOrg.setSupportOrganizationID(supportOrganizations.getSupportOrganizationId());
                    supportOrg.setOrganizationName(supportOrganizations.getOrganizationName());
                    GroupID id = new GroupID();
                    id.setValue(groupTranslations.getId().getGroupId());
                    id.setGroupName(groupTranslations.getGroupName());
                    supportOrg.setGroupID(id);
                    supportCase.getSupportOrganizations().add(supportOrg);
                }
            }
        }
        supportCases.getSupportCase().add(supportCase);
        tingcoSupport.setSupportCases(supportCases);
        return tingcoSupport;
    }

    public TingcoSupportCase buildGetAllSupportOrganizations(TingcoSupportCase tingcoSupport, List<SupportOrganizations> filteredSO) {
        SupportCases supportCases = new SupportCases();
        SupportCase supportCase = new SupportCase();
        for (SupportOrganizations supportOrganizations : filteredSO) {
            se.info24.supportcasejaxb.SupportOrganizations soJaxb = new se.info24.supportcasejaxb.SupportOrganizations();
            soJaxb.setSupportOrganizationID(supportOrganizations.getSupportOrganizationId());
            soJaxb.setOrganizationName(supportOrganizations.getOrganizationName());
            supportCase.getSupportOrganizations().add(soJaxb);
        }
        supportCases.getSupportCase().add(supportCase);
        tingcoSupport.setSupportCases(supportCases);
        return tingcoSupport;
    }
}
