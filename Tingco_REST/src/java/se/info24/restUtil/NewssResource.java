/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package se.info24.restUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.xml.datatype.DatatypeConfigurationException;
import org.hibernate.Session;
import se.info24.group.GroupDAO;
import se.info24.jaxbUtil.TingcoUtilsXML;
import se.info24.pojo.GroupTrusts;
import se.info24.pojo.Ismnews;
import se.info24.pojo.NewsCategories;
import se.info24.pojo.NewsCategoryTranslations;
import se.info24.pojo.NewsVisibleInGroups;
import se.info24.pojo.NewsVisibleInGroupsId;
import se.info24.pojo.UserSessions2;
import se.info24.pojo.UserTimeZones2;
import se.info24.pojo.Users2;
import se.info24.user.UserDAO;
import se.info24.user.User_LoginsResource;
import se.info24.util.HibernateUtil;
import se.info24.util.JAXBManager;
import se.info24.util.RESTDoc;
import se.info24.util.TCMUtil;
import se.info24.utiljaxb.GroupID;
import se.info24.utiljaxb.News;
import se.info24.utiljaxb.TingcoUtils;

/**
 * REST Web Service
 *
 * @author Sekhar
 */
@Path("/news")
public class NewssResource {

    @Context
    private UriInfo context;
    @Context
    private HttpServletRequest request;
    TingcoUtilsXML tuXML = new TingcoUtilsXML();
    RestUtilDAO restUtilDao = new RestUtilDAO();
    UserDAO userDAO = new UserDAO();

    /** Creates a new instance of NewssResource */
    public NewssResource() {
    }

    /**
     * News_Delete
     * @param newsID
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/newsid/{newsid}")
    @RESTDoc(methodName = "News_Delete", desc = "To Delete News", req_Params = {"NewsID;int"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_Delete(@PathParam("newsid") String newsID) {
        return deleteNews(newsID);
    }

    /**
     * News_Delete
     * @param newsID
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/delete/rest/{rest}/newsid/{newsid}")
    public TingcoUtils postXml_Delete(@PathParam("newsid") String newsID) {
        return deleteNews(newsID);
    }

    /**
     * News_Delete
     * @param newsID
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/delete/json/{json}/newsid/{newsid}")
    public TingcoUtils getJson_Delete(@PathParam("newsid") String newsID) {
        return deleteNews(newsID);
    }

    /**
     * News_Delete
     * @param newsID
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/delete/json/{json}/newsid/{newsid}")
    public TingcoUtils postJson_Delete(@PathParam("newsid") String newsID) {
        return deleteNews(newsID);
    }

    /**
     * News_Add
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/add/rest/{rest}")
    @RESTDoc(methodName = "News_Add", desc = "To Add News(Post XML Call)", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoUtils postXml_Add(String content) {
        return addNews(content);
    }

    /**
     * News_Add
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/add/json/{json}")
    public TingcoUtils postJson_Add(String content) {
        return addNews(content);
    }

    /**
     * News_Update
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/update/rest/{rest}")
    @RESTDoc(methodName = "News_Update", desc = "To Update News(Post XML Call)", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoUtils postXml_Update(String content) {
        return updateNews(content);
    }

    /**
     * News_Update
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/update/json/{json}")
    public TingcoUtils postJson_Update(String content) {
        return updateNews(content);
    }

    /**
     * GetAllNews
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/rest/{rest}")
    @RESTDoc(methodName = "GetAllNews", desc = "Get All News", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_All() {
        return getAllNews();
    }

    /**
     * GetAllNews
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/json/{json}")
    public TingcoUtils getJson_All() {
        return getAllNews();
    }

    /**
     * GetAllNews
     * @return
     */
    @POST
    @Produces("application/xml")
    @Path("/rest/{rest}")
    public TingcoUtils postXml_All() {
        return getAllNews();
    }

    /**
     * GetAllNews
     * @return
     */
    @POST
    @Produces("application/json")
    @Path("/json/{json}")
    public TingcoUtils postJson_All() {
        return getAllNews();
    }

    /**
     * GetNewsCategoryList
     * @param countryId
     * @param searchString
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/getnewscategorylist/rest/{rest}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    @RESTDoc(methodName = "GetNewsCategoryList", desc = "GetNewsCategoryList", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_GetNewsCategoryList(@PathParam("countryid") int countryId, @PathParam("searchstring") String searchString) {
        return getNewsCategoryList(countryId, searchString);
    }

    /**
     * GetNewsCategoryList
     * @param countryId
     * @param searchString
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/getnewscategorylist/json/{json}/countryid/{countryid}{searchstring:(/searchstring/[^/]+?)?}")
    public TingcoUtils getJson_GetNewsCategoryList(@PathParam("countryid") int countryId, @PathParam("searchstring") String searchString) {
        return getNewsCategoryList(countryId, searchString);
    }

    /**
     * AddNews
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/xml")
    @Path("/addnews/rest/{rest}")
    @RESTDoc(methodName = "News_Add", desc = "To Add News(Post XML Call)", req_Params = {"rest;string"}, opt_Params = {}, method_formats = {"REST"}, method_requests = {"POST"}, versions = {"1"})
    public TingcoUtils postXml_AddNews(String content) {
        return addNewNews(content);
    }

    /**
     * AddNews
     * @param content
     * @return
     */
    @POST
    @Consumes("application/xml")
    @Produces("application/json")
    @Path("/addnews/json/{json}")
    public TingcoUtils postJson_AddNews(String content) {
        return addNewNews(content);
    }

    @GET
    @Produces("application/xml")
    @Path("/getnews/rest/{rest}/countryid/{countryid}{newsid:(/newsid/[^/]+?)?}")
    @RESTDoc(methodName = "GetNews", desc = "GetNews", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_GetNews(@PathParam("countryid") int countryId, @PathParam("newsid") String newsId) {
        return getNews(countryId, newsId);
    }

    @GET
    @Produces("application/json")
    @Path("/getnews/json/{json}/countryid/{countryid}{newsid:(/newsid/[^/]+?)?}")
    public TingcoUtils getJson_GetNews(@PathParam("countryid") int countryId, @PathParam("newsid") String newsId) {
        return getNews(countryId, newsId);
    }

    /**
     * UpdateNewsDetails
     * @param content
     * @return
     */
    @POST
    @Produces("application/xml")
    @Consumes("application/xml")
    @Path("/updatenewsdetails/rest/{rest}")
    @RESTDoc(methodName = "UpdateNewsDetails", desc = "UpdateNewsDetails", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_UpdateNewsDetails(String content) {
        return updateNewsDetails(content);
    }

    /**
     * UpdateNewsDetails
     * @param content
     * @return
     */
    @POST
    @Produces("application/json")
    @Consumes("application/xml")
    @Path("/updatenewsdetails/json/{json}")
    public TingcoUtils getJson_UpdateNewsDetails(String content) {
        return updateNewsDetails(content);
    }

    /**
     * DeleteNewsDetails
     * @param newsid
     * @return
     */
    @GET
    @Produces("application/xml")
    @Path("/deletenewsdetails/rest/{rest}/newsid/{newsid}")
    @RESTDoc(methodName = "DeleteNewsDetails", desc = "Delete News Details", req_Params = {}, opt_Params = {}, method_formats = {"REST", "JSON"}, method_requests = {"GET", "POST"}, versions = {"1"})
    public TingcoUtils getXml_DeleteNewsDetails(@PathParam("newsid") String newsid) {
        return deleteNewsDetails(newsid);
    }

    /**
     * DeleteNewsDetails
     * @param newsid
     * @return
     */
    @GET
    @Produces("application/json")
    @Path("/deletenewsdetails/json/{json}/newsid/{newsid}")
    public TingcoUtils getJson_DeleteNewsDetails(@PathParam("newsid") String newsid) {
        return deleteNewsDetails(newsid);
    }

    private TingcoUtils deleteNewsDetails(String newsid) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoUtils tingcoUtils = null;
        boolean hasPermission = false;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.NEWS, TCMUtil.DELETE);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Object object = restUtilDao.getNewsByNewsID(newsid, session);

                    if (object == null) {
                        tingcoUtils.getMsgStatus().setResponseCode(-1);
                        tingcoUtils.getMsgStatus().setResponseText("No Data Found");
                        return tingcoUtils;
                    } else {
                        se.info24.pojo.News news = (se.info24.pojo.News) object;
                        List<NewsVisibleInGroups> newsVisibleInGroupses = restUtilDao.getNewsVisibleInGroupsByNewsID(newsid, session);
                        if (!restUtilDao.deleteNews(news, newsVisibleInGroupses, session)) {
                            tingcoUtils.getMsgStatus().setResponseCode(-1);
                            tingcoUtils.getMsgStatus().setResponseText("Error Occurred While Delete");
                            return tingcoUtils;
                        }
                    }
                } else {
                    tingcoUtils.getMsgStatus().setResponseCode(-10);
                    tingcoUtils.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUtils;
                }
            } else {
                tingcoUtils.getMsgStatus().setResponseCode(-3);
                tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUtils;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUtils.getMsgStatus().setResponseCode(-1);
            tingcoUtils.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUtils;
    }

    private TingcoUtils updateNewsDetails(String content) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoUtils tingcoUtils = null;
        boolean hasPermission = false;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.NEWS, TCMUtil.UPDATE);
                if (hasPermission) {
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                    session = HibernateUtil.getSessionFactory().openSession();
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        TingcoUtils utils = (TingcoUtils) JAXBManager.getInstance().unMarshall(content, TingcoUtils.class);
                        List<News> list = utils.getNews();
                        if (list.isEmpty()) {
                            tingcoUtils.getMsgStatus().setResponseCode(-1);
                            tingcoUtils.getMsgStatus().setResponseText("No News Data In Post XMl");
                            return tingcoUtils;
                        } else {
                            News newsXml = list.get(0);
                            se.info24.pojo.News modifiedNews = null;
                            Object object = restUtilDao.getNewsByNewsID(newsXml.getNewsIDTCMV3(), session);
                            if (object == null) {
                                tingcoUtils.getMsgStatus().setResponseCode(-1);
                                tingcoUtils.getMsgStatus().setResponseText("No Data Found");
                                return tingcoUtils;
                            } else {
                                modifiedNews = (se.info24.pojo.News) object;
                                if (newsXml.getSubject() != null) {
                                    modifiedNews.setSubject(TCMUtil.convertHexToString(newsXml.getSubject()));
                                }
                                if (newsXml.getBody() != null) {
                                    modifiedNews.setBody(TCMUtil.convertHexToString(newsXml.getBody()));
                                }
                                modifiedNews.setUpdatedDate(gc.getTime());
                                modifiedNews.setLastUpdatedByUserId(sessions2.getUserId());
                            }

                            if (!restUtilDao.addNewNews(modifiedNews, new ArrayList<NewsVisibleInGroups>(), session)) {
                                tingcoUtils.getMsgStatus().setResponseCode(-1);
                                tingcoUtils.getMsgStatus().setResponseText("Error occured While updating news");
                                return tingcoUtils;
                            }
                        }
                    }
                } else {
                    tingcoUtils.getMsgStatus().setResponseCode(-10);
                    tingcoUtils.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUtils;
                }
            } else {
                tingcoUtils.getMsgStatus().setResponseCode(-3);
                tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUtils;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUtils.getMsgStatus().setResponseCode(-1);
            tingcoUtils.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUtils;
    }

    private TingcoUtils getNews(int countryId, String newsId) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoUtils tingcoUtils = null;
        boolean hasPermission = false;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.NEWS, TCMUtil.READ);
                if (!newsId.equals("")) {
                    newsId = newsId.split("/")[2];
                } else {
                    newsId = null;
                }
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    Users2 users2 = userDAO.getUserById(sessions2.getUserId(), session);
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        Set<String> categoryIds = new HashSet<String>();
                        if (newsId != null) {
                            Object object = restUtilDao.getNewsByNewsID(newsId, session);
                            if (object == null) {
                                tingcoUtils.getMsgStatus().setResponseCode(-1);
                                tingcoUtils.getMsgStatus().setResponseText("No Data Found With given NewID");
                                return tingcoUtils;
                            } else {
                                se.info24.pojo.News n = (se.info24.pojo.News) object;
                                List<se.info24.pojo.News> selectedNew = new ArrayList<se.info24.pojo.News>();
                                selectedNew.add(n);
                                List<Users2> users2s = new ArrayList<Users2>();
                                users2s.add(userDAO.getUserById(n.getLastUpdatedByUserId(), session));
                                categoryIds.add(n.getNewsCategories().getNewsCategoryId());
                                List<NewsCategoryTranslations> categoryTranslationses = restUtilDao.getNewsCategoryTranslationsByNewsCategoryIDs(session, countryId, categoryIds);
                                tingcoUtils = tuXML.buildGetNewsXML(tingcoUtils, selectedNew, categoryTranslationses, timeZoneGMToffset, users2s, users2.getGroupId());
                            }
                        } else {
                            List<se.info24.pojo.News> newses = new ArrayList<se.info24.pojo.News>();
                            List<NewsCategoryTranslations> categoryTranslationses = new ArrayList<NewsCategoryTranslations>();
                            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
                            List<NewsVisibleInGroups> newsVisibleInGroupses = restUtilDao.getNewsVisibleInGroupsByGroupID(users2.getGroupId(), session);
                            if (newsVisibleInGroupses.isEmpty()) {
                                newses.addAll(restUtilDao.getNewsByIsVisibleToAllGroupsAndDate(session, df.format(gc.getTime())));
                            } else {
                                List<String> newsIDs = new ArrayList<String>();
                                for (NewsVisibleInGroups nvig : newsVisibleInGroupses) {
                                    newsIDs.add(nvig.getId().getNewsId());
                                }
                                newses.addAll(restUtilDao.getNewsByGroupIDAndDate(session, newsIDs, df.format(gc.getTime())));
                            }
                            if (newses.isEmpty()) {
                                tingcoUtils.getMsgStatus().setResponseCode(-1);
                                tingcoUtils.getMsgStatus().setResponseText("No Data Found");
                                return tingcoUtils;
                            } else {
                                Set<String> userId = new HashSet<String>();
                                for (se.info24.pojo.News news : newses) {
                                    categoryIds.add(news.getNewsCategories().getNewsCategoryId());
                                    userId.add(news.getLastUpdatedByUserId());
                                }
                                List<Users2> users2s = userDAO.getUsers2ByUserIdShorted(new ArrayList<String>(userId), session);
                                categoryTranslationses.addAll(restUtilDao.getNewsCategoryTranslationsByNewsCategoryIDs(session, countryId, categoryIds));
                                tingcoUtils = tuXML.buildGetNewsXML(tingcoUtils, newses, categoryTranslationses, timeZoneGMToffset, users2s, users2.getGroupId());
                            }
                        }
                    } else {
                        tingcoUtils.getMsgStatus().setResponseCode(-1);
                        tingcoUtils.getMsgStatus().setResponseText("UserTimezone not found. Unable to display the data in UserTimezone");
                        return tingcoUtils;
                    }
                } else {
                    tingcoUtils.getMsgStatus().setResponseCode(-10);
                    tingcoUtils.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUtils;
                }
            } else {
                tingcoUtils.getMsgStatus().setResponseCode(-3);
                tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUtils;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUtils.getMsgStatus().setResponseCode(-1);
            tingcoUtils.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUtils;
    }

    private TingcoUtils addNewNews(String content) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoUtils tingcoUtils = null;
        boolean hasPermission = false;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.NEWS, TCMUtil.ADD);
                if (hasPermission) {
                    GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
                    session = HibernateUtil.getSessionFactory().openSession();

                    Users2 users = userDAO.getUserById(sessions2.getUserId(), session);
                    UserTimeZones2 userTimeZones2 = RestUtilDAO.getUserTimeZones2byUserId(sessions2.getUserId(), session);
                    if (userTimeZones2 != null) {
                        String timeZoneGMToffset = RestUtilDAO.getTimezoneById(userTimeZones2.getTimeZoneId(), session).getTimeZoneGmtoffset();
                        TingcoUtils utils = (TingcoUtils) JAXBManager.getInstance().unMarshall(content, TingcoUtils.class);
                        List<News> list = utils.getNews();
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        if (list.isEmpty()) {
                            tingcoUtils.getMsgStatus().setResponseCode(-1);
                            tingcoUtils.getMsgStatus().setResponseText("No News Data In Post XMl");
                            return tingcoUtils;
                        } else {
                            News newsXml = list.get(0);
                            List<GroupID> groupIDs = newsXml.getGroupID();

                            List<se.info24.utiljaxb.NewsCategories> newsCategorieses = newsXml.getNewsCategories();

                            se.info24.pojo.News newNews = new se.info24.pojo.News();

                            List<NewsVisibleInGroups> newsVisibleInGroupsList = new ArrayList<NewsVisibleInGroups>();

                            final String newsId = UUID.randomUUID().toString().toUpperCase();
                            newNews.setNewsId(newsId);
                            newNews.setLastUpdatedByUserId(sessions2.getUserId());
                            newNews.setGroupId(users.getGroupId());
                            if (newsXml.getIsEnabled() != null) {
                                newNews.setIsEnabled(newsXml.getIsEnabled().intValue());
                            }
                            if (newsXml.getIsVisibleToAllGroups() != null) {
                                newNews.setIsVisibleToAllGroups(newsXml.getIsVisibleToAllGroups().intValue());
                            }
                            if (newsXml.getSubject() != null) {
                                newNews.setSubject(TCMUtil.convertHexToString(newsXml.getSubject()));
                            }
                            if (newsXml.getBody() != null) {
                                newNews.setBody(TCMUtil.convertHexToString(newsXml.getBody()));
                            }
                            if (!newsCategorieses.isEmpty()) {
                                se.info24.utiljaxb.NewsCategories categoriesFromXMl = newsCategorieses.get(0);
                                NewsCategories categories = (NewsCategories) restUtilDao.getNewsCategoriesByNewsCategoryID(session, categoriesFromXMl.getNewsCategoryID());
                                newNews.setNewsCategories(categories);
                            }
                            boolean flag = false;

                            if (!groupIDs.isEmpty()) {
                                for (GroupID groupID : groupIDs) {
                                    if (groupID.getValue().equalsIgnoreCase(users.getGroupId())) {
                                        flag = true;
                                    }
                                    NewsVisibleInGroups newsVisibleInGroups = new NewsVisibleInGroups();
                                    newsVisibleInGroups.setId(new NewsVisibleInGroupsId(newsId, groupID.getValue()));
                                    newsVisibleInGroups.setCreatedDate(gc.getTime());
                                    newsVisibleInGroups.setUpdatedDate(gc.getTime());
                                    newsVisibleInGroups.setLastUpdatedByUserId(sessions2.getUserId());
                                    newsVisibleInGroupsList.add(newsVisibleInGroups);
                                }
                            }
                            if (!flag) {
                                NewsVisibleInGroups newsVisibleInGroups = new NewsVisibleInGroups();
                                newsVisibleInGroups.setId(new NewsVisibleInGroupsId(newsId, users.getGroupId()));
                                newsVisibleInGroups.setCreatedDate(gc.getTime());
                                newsVisibleInGroups.setUpdatedDate(gc.getTime());
                                newsVisibleInGroups.setLastUpdatedByUserId(sessions2.getUserId());
                                newsVisibleInGroupsList.add(newsVisibleInGroups);
                            }
                            newNews.setCreatedDate(gc.getTime());
                            newNews.setUpdatedDate(gc.getTime());
                            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, df.parse(newsXml.getActiveFromDate())));
                            newNews.setActiveFromDate(gc.getTime());
                            gc.setTime(RestUtilDAO.convertUserLocalTimetoGMT(timeZoneGMToffset, df.parse(newsXml.getActiveToDate())));
                            newNews.setActiveToDate(gc.getTime());
                            if (!restUtilDao.addNewNews(newNews, newsVisibleInGroupsList, session)) {
                                tingcoUtils.getMsgStatus().setResponseCode(-1);
                                tingcoUtils.getMsgStatus().setResponseText("Error occured While adding news");
                                return tingcoUtils;
                            }
                        }
                    }
                } else {
                    tingcoUtils.getMsgStatus().setResponseCode(-10);
                    tingcoUtils.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUtils;
                }
            } else {
                tingcoUtils.getMsgStatus().setResponseCode(-3);
                tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUtils;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUtils.getMsgStatus().setResponseCode(-1);
            tingcoUtils.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUtils;
    }

    private TingcoUtils getNewsCategoryList(int coutryId, String searchString) {
        long requestedTime = System.currentTimeMillis();
        Session session = null;
        TingcoUtils tingcoUtils = null;
        boolean hasPermission = false;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
            if (request.getAttribute("USERSESSION") != null) {
                if (!searchString.equals("")) {
                    searchString = searchString.split("/")[2];
                } else {
                    searchString = null;
                }
                UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
                hasPermission = getPermission(sessions2.getUserId(), TCMUtil.NEWS, TCMUtil.READ);
                if (hasPermission) {
                    session = HibernateUtil.getSessionFactory().openSession();
                    List<NewsCategoryTranslations> categoryTranslationses = new ArrayList<NewsCategoryTranslations>();
                    if (searchString == null) {
                        List<NewsCategories> newsCategorieses = restUtilDao.getAllNewsCategories(session);
                        if (newsCategorieses.isEmpty()) {
                            tingcoUtils.getMsgStatus().setResponseCode(-1);
                            tingcoUtils.getMsgStatus().setResponseText("No Data Found");
                            return tingcoUtils;
                        } else {
                            Set<String> categoryIDs = new HashSet<String>();
                            for (NewsCategories nc : newsCategorieses) {
                                categoryIDs.add(nc.getNewsCategoryId());
                            }
                            categoryTranslationses.addAll(restUtilDao.getNewsCategoryTranslationsByNewsCategoryIDs(session, coutryId, categoryIDs, 200));
                        }
                    } else {
                        categoryTranslationses.addAll(restUtilDao.getNewsCategoryTranslationsBySearchString(session, coutryId, searchString));
                    }
                    tingcoUtils = tuXML.buildGetNewsCategoryList(tingcoUtils, categoryTranslationses);
                } else {
                    tingcoUtils.getMsgStatus().setResponseCode(-10);
                    tingcoUtils.getMsgStatus().setResponseText("User Permission is not given");
                    return tingcoUtils;
                }
            } else {
                tingcoUtils.getMsgStatus().setResponseCode(-3);
                tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
                return tingcoUtils;
            }
        } catch (Exception ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
            tingcoUtils.getMsgStatus().setResponseCode(-1);
            tingcoUtils.getMsgStatus().setResponseText("Error occured");
        } finally {
            if (session != null) {
                session.close();
            }
            delayLog(requestedTime);
        }
        return tingcoUtils;
    }

    private TingcoUtils addNews(String content) {
        TingcoUtils tingcoUtils = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        if (request.getAttribute("USERSESSION") != null) {
            TingcoUtils utils = (TingcoUtils) JAXBManager.getInstance().unMarshall(content, TingcoUtils.class);
            ArrayList<Ismnews> newsList = new ArrayList<Ismnews>();
            UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
            Session session = HibernateUtil.getSessionFactory().openSession();
            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
            try {
                for (News news : utils.getNews()) {
                    Ismnews ismNews = new Ismnews();
                    if (news.getSubject() != null) {
                        ismNews.setSubject(news.getSubject());
                    }
                    if (news.getBody() != null) {
                        ismNews.setBody(news.getBody());
                    }
                    ismNews.setUserId(sessions2.getUserId());
                    ismNews.setCreatedDate(gc.getTime());
                    ismNews.setUpdatedDate(gc.getTime());
                    newsList.add(ismNews);
                }

                for (Ismnews news : newsList) {
                    restUtilDao.saveNews(news, session);
                }
                return tingcoUtils;
            } catch (Exception e) {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("Error Occured While Updating the News");
                return tingcoUtils;
            } finally {
                if (session != null) {
                    session.close();
                }
            }

        } else {
            tingcoUtils.getMsgStatus().setResponseCode(-3);
            tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
            return tingcoUtils;
        }
    }

    private TingcoUtils getAllNews() {
        TingcoUtils tingcoUtils = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
        } catch (DatatypeConfigurationException dce) {
            TCMUtil.exceptionLog(getClass().getName(), dce);
        }
        if (request.getAttribute("USERSESSION") != null) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            try {
                List<Ismnews> newsList = restUtilDao.getAllNews(session);
                if (newsList != null) {
                    HashMap<String, Users2> userMap = new HashMap<String, Users2>();
                    for (Ismnews news : newsList) {
                        if (!userMap.containsKey(news.getUserId())) {
                            Users2 user2 = userDAO.getUserById(news.getUserId(), session);
                            userMap.put(news.getUserId(), user2);
                        }
                    }
                    tingcoUtils = tuXML.buildNews(tingcoUtils, newsList, userMap);
                    return tingcoUtils;
                } else {
                    tingcoUtils.getMsgStatus().setResponseCode(-1);
                    tingcoUtils.getMsgStatus().setResponseText("Error Occured while Loading the News");
                    return tingcoUtils;
                }
            } finally {
                session.close();
            }
        } else {
            tingcoUtils.getMsgStatus().setResponseCode(-3);
            tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
            return tingcoUtils;
        }
    }

    private TingcoUtils deleteNews(String newsId) {
        TingcoUtils tingcoUtils = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }

        if (request.getAttribute("USERSESSION") != null) {
            if (newsId == null) {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("NewsID should not be empty");
                return tingcoUtils;
            }
            Session session = HibernateUtil.getSessionFactory().openSession();
            Ismnews news = restUtilDao.getNewsById(newsId, session);
            if (news != null) {
                try {
                    if (restUtilDao.removeNews(news, session)) {
                        return tingcoUtils;
                    } else {
                        tingcoUtils.getMsgStatus().setResponseCode(-1);
                        tingcoUtils.getMsgStatus().setResponseText("Error Occured while Deleting News");
                        return tingcoUtils;
                    }
                } finally {
                    session.close();
                }
            } else {
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("NewsID Not Found");
                return tingcoUtils;
            }
        } else {
            tingcoUtils.getMsgStatus().setResponseCode(-3);
            tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
            return tingcoUtils;
        }
    }

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public NewsResource getNews_AddResource() {
        return new NewsResource();
    }

    private TingcoUtils updateNews(String content) {
        TingcoUtils tingcoUtils = null;
        try {
            tingcoUtils = tuXML.buildTingcoUtilsTemplate();
        } catch (DatatypeConfigurationException ex) {
            TCMUtil.exceptionLog(getClass().getName(), ex);
        }
        if (request.getAttribute("USERSESSION") != null) {
            TingcoUtils utils = (TingcoUtils) JAXBManager.getInstance().unMarshall(content, TingcoUtils.class);
            ArrayList<Ismnews> newsList = new ArrayList<Ismnews>();
            UserSessions2 sessions2 = (UserSessions2) request.getAttribute("USERSESSION");
            Session session = HibernateUtil.getSessionFactory().openSession();
            GregorianCalendar gc = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
            try {
                for (News news : utils.getNews()) {
                    Ismnews ismNews = null;
                    if (news.getNewsID() > 0) {
                        ismNews = restUtilDao.getNewsById(String.valueOf(news.getNewsID()), session);
                    }
                    if (ismNews != null) {
                        if (news.getSubject() != null) {
                            ismNews.setSubject(news.getSubject());
                        }
                        if (news.getBody() != null) {
                            ismNews.setBody(news.getBody());
                        }
                        ismNews.setUserId(sessions2.getUserId());
                        ismNews.setUpdatedDate(gc.getTime());
                        newsList.add(ismNews);
                    } else {
                        tingcoUtils.getMsgStatus().setResponseCode(-1);
                        tingcoUtils.getMsgStatus().setResponseText("NewsID Not Found");
                        return tingcoUtils;
                    }
                }

                for (Ismnews news : newsList) {
                    restUtilDao.saveNews(news, session);
                }
                return tingcoUtils;
            } catch (Exception e) {
                TCMUtil.exceptionLog(getClass().getName(), e);
                tingcoUtils.getMsgStatus().setResponseCode(-1);
                tingcoUtils.getMsgStatus().setResponseText("Error Occured While Updating the News");
                return tingcoUtils;
            } finally {
                if (session != null) {
                    session.close();
                }
            }

        } else {
            tingcoUtils.getMsgStatus().setResponseCode(-3);
            tingcoUtils.getMsgStatus().setResponseText("User Session is Not Valid");
            return tingcoUtils;
        }
    }

    public void delayLog(long requestedTime) {
        TCMUtil.loger(getClass().getName(), " [tingco API] [Request took " + (System.currentTimeMillis() - requestedTime) + "ms]", "Info");
    }

    private boolean getPermission(String userId, String functionarea, String operation) {
        boolean hasPermission = false;
        Hashtable<String, ArrayList> ht = (Hashtable<String, ArrayList>) User_LoginsResource.permissions.get(userId);
        if (ht.containsKey(functionarea)) {
            ArrayList<String> operations = ht.get(functionarea);
            for (int i = 0; i < operations.size(); i++) {
                if (operations.get(i).equalsIgnoreCase(operation)) {
                    hasPermission = true;
                    break;
                }
            }
        }
        return hasPermission;
    }
}
