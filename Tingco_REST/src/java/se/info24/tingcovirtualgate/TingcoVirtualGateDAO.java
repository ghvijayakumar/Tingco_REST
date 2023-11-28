/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.tingcovirtualgate;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.Transaction;
import se.info24.pojo.ContainerUserAlias;
import se.info24.pojo.Containers;
import se.info24.pojo.Ogmdevice;
import se.info24.pojo.OgmuserAlias;
import se.info24.pojo.UserAlias;
import se.info24.pojo.UserAliasTypes;
import se.info24.util.TCMUtil;

/**
 *
 * @author Vijayakumar
 */
public class TingcoVirtualGateDAO {

    public Object getObjectConnectedStateByObjectIdAndServiceId(String objectId, String serviceId, Session ismOperationssession) {
        return ismOperationssession.getNamedQuery("getObjectConnectedStateByObjectIdServiceId").setString("objectId", objectId).setString("lastContactServiceId", serviceId).uniqueResult();
    }

    public Object getContainerUserAliasByIds(String containerId, String userAliasId, Session session) {
        return session.getNamedQuery("getContainerUserAliasByIds").setString("containerId", containerId).setString("userAliasId", userAliasId).uniqueResult();
    }

    List<Ogmdevice> getOgmdeviceByDeviceId(Session session, String deviceId) {
        return session.getNamedQuery("getOgmdeviceByDeviceId").setString("deviceId", deviceId).list();
    }

    List<OgmuserAlias> getOgmuserAliasByObjectGroupIdList(Session session, Set<String> objectGroupIDs) {
        return session.getNamedQuery("getOgmuserAliasByObjectGroupIdList").setParameterList("objectGroupId", objectGroupIDs).list();
    }

    List<UserAlias> getUserAliasDetailsByUserAliasIDsAndValidDate(Session session, List<String> userAliasIDs, String currentTime) {
        TCMUtil.loger(getClass().getName(), currentTime, "Info");
        return session.getNamedQuery("getUserAliasDetailsByUserAliasIDsAndValidDate").setParameterList("userAliasId", userAliasIDs).setString("timenow", currentTime).list();
    }

    List<UserAliasTypes> getUserAliasTypesByIDList(Session session, Set<String> userAliasTypeIDs) {
        return session.getNamedQuery("getUserAliasTypesByIDList").setParameterList("userAliasTypeId", userAliasTypeIDs).list();
    }

    public List<ContainerUserAlias> getContainerUserAliasBycontainerId(String containerId, Session session) {
        return session.getNamedQuery("getContainerUserAliasBycontainerId").setString("containerId", containerId).list();
    }

    public boolean updatecontainers(Session session, String containerId) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            GregorianCalendar gc = new GregorianCalendar();
            List<ContainerUserAlias> containerUserAliases = getContainerUserAliasBycontainerId(containerId, session);
            Containers containers = (Containers) session.get(Containers.class, containerId);
            containers.setCurrentFillLevel(new BigDecimal(containerUserAliases.size()));
            containers.setUpdatedDate(gc.getTime());
            tx.commit();
            return true;
        } catch (Exception e) {
            if(tx != null){
                tx.rollback();
            }
            TCMUtil.exceptionLog(getClass().getName(), e);
            return false;
        }
    }
}
