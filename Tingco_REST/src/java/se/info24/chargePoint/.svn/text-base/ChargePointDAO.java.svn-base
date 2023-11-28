/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.info24.chargePoint;

import java.util.List;
import org.hibernate.Session;
import se.info24.ismOperationsPojo.DeviceActivationSchedules;
import se.info24.ismOperationsPojo.DeviceStatus;
import se.info24.ismOperationsPojo.ObjectUsageRecords;

/**
 *
 * @author Hitha
 */
public class ChargePointDAO {

    public List<DeviceActivationSchedules> getDeviceActivationSchedulesByDeviceId(String deviceId, int maxResult, Session session) {
        return session.getNamedQuery("getDeviceActivationSchedulesByDeviceId").setString("deviceId", deviceId).setMaxResults(maxResult).list();
    }

    public List<ObjectUsageRecords> getStatusChart(String groupId, List<String> d, Session session) {
        return session.getNamedQuery("getObjectUsageRecordsByDeviceId").setParameterList("deviceId",d).list();
    }

    public List<DeviceStatus> getStatusLocationByDeviceID(String deviceId, Session session) {
        return session.getNamedQuery("getDeviceStatusByDeviceIDandIsEnabled").setString("deviceId", deviceId).list();
    }

    void getDetailsByUserID(String userId, Session msession) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
