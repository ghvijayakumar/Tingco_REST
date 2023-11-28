/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.tim;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import org.hibernate.Session;
import se.info24.timPojo.SiteMenuTranslations;
import se.info24.timjaxb.Menu;
import se.info24.timjaxb.MsgStatus;
import se.info24.timjaxb.ObjectFactory;
import se.info24.timjaxb.TingcoMobile;
import se.info24.timjaxb.UserMenu;
import se.info24.util.TCMUtil;

/**
 *
 * @author Vijayakumar
 */
public class TingcoMobileXML {

    private TingcoMobile tingcoMobile;
    ObjectFactory objectFactory;

    public TingcoMobileXML() {
        objectFactory = new ObjectFactory();
    }

    public TingcoMobile buildTingcoMobileTemplate() throws DatatypeConfigurationException {
        tingcoMobile = objectFactory.createTingcoMobile();
        tingcoMobile.setCreateRef("Info24");
        tingcoMobile.setMsgVer(new BigDecimal("1.0"));
        tingcoMobile.setRegionalUnits("Metric");
        tingcoMobile.setTimeZone("UTC");

        tingcoMobile.setMsgID(UUID.randomUUID().toString());
        tingcoMobile.setMsgTimeStamp(DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar(TimeZone.getTimeZone("GMT"))));

        MsgStatus msgStatus = new MsgStatus();
        msgStatus.setResponseCode(0);
        msgStatus.setResponseText("OK");

        tingcoMobile.setMsgStatus(msgStatus);
        return tingcoMobile;
    }

    public TingcoMobile buildGetCommandDetailsByDeviceId(TingcoMobile tingcoMobile, List<Object> timMenuDetailsList,Session session, int countryID) {
        UserMenu userMenu = new UserMenu();
        TingcoMobileDAO dao = new TingcoMobileDAO();
//cpt.commandid, cpt.targetstring, ct.commandname, cpt.devicetypecommandid, dtct.commandname
        for (Iterator itr = timMenuDetailsList.iterator(); itr.hasNext();) {
            Object[] row = (Object[]) itr.next();
            Menu menu = new Menu();
            for (int i = 0; i < row.length; i++) {
                if (i % 2 == 0) {
                    if (row[i] != null) {
                        menu.setSiteMenuNodeID(row[i].toString());
                    }
                    if (row[i + 1] != null) {
                        menu.setSiteMenuNodeName(row[i + 1].toString());
                    }
                    if (row[i + 2] != null) {
                        String parentId = row[i + 2].toString();
                        menu.setSiteMenuNodeParentID(parentId);
                        Object obj =dao.getSiteMenuTranslationsById(session,parentId, countryID);
                        if(obj != null){
                            SiteMenuTranslations smt = (SiteMenuTranslations) obj;
                            menu.setSiteMenuNodeParentName(smt.getSiteMenuNodeName());
                        }
                    } else {
                        menu.setSiteMenuNodeParentID("NULL"); //Added Null when parentid is null in the table, as per the requirement in TIM from .NET Team.
                        menu.setSiteMenuNodeParentName("NULL");
                    }
                    if (row[i + 3] != null) {
                        menu.setPageID(row[i + 3].toString());
                    }
                    if (row[i + 4] != null) {
                        menu.setPageName(row[i + 4].toString());
                    }
                    if (row[i + 5] != null) {
                        menu.setSortingWeight(row[i + 5].toString());
                    }
                    if (row[i + 6] != null) {
                        menu.setPageTitle(row[i + 6].toString());
                    }
                    if (row[i + 7] != null) {
                        menu.setNodeIconURL(row[i + 7].toString());
                    }
                    i += 7;
                }
                userMenu.getMenu().add(menu);
            }
        }
        tingcoMobile.setUserMenu(userMenu);
        return tingcoMobile;
    }
}
