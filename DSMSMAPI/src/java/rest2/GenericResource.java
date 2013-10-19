/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rest2;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import org.tmforum.smi.configurationdataex.ConfigData;
import org.tmforum.smi.configurationdataex.NameValuePair;
import org.tmforum.xml.tip.internal.primitives.ArrayOfString;
import org.tmforum.xml.tip.smi.ArrayOfMetric;
import org.tmforum.xml.tip.smi.ExecutionState;
import org.tmforum.xml.tip.smi.ExecutionStateAction;
import org.tmforum.xml.tip.smi.GetExecutionStateRequest;
import org.tmforum.xml.tip.smi.GetExecutionStateResponse;
import org.tmforum.xml.tip.smi.GetManagementReportRequest;
import org.tmforum.xml.tip.smi.GetManagementReportResponse;
import org.tmforum.xml.tip.smi.GetServiceConfigurationRequest;
import org.tmforum.xml.tip.smi.GetServiceConfigurationResponse;
import org.tmforum.xml.tip.smi.HealthState;
import org.tmforum.xml.tip.smi.ManagementReport;
import org.tmforum.xml.tip.smi.ManagementReportFilter;
import org.tmforum.xml.tip.smi.Metric;
import org.tmforum.xml.tip.smi.MetricFilter;
import org.tmforum.xml.tip.smi.ObjectFactory;
import org.tmforum.xml.tip.smi.Pagination;
import org.tmforum.xml.tip.smi.RegisterListenerRequest;
import org.tmforum.xml.tip.smi.RegisterListenerResponse;
import org.tmforum.xml.tip.smi.ServiceConfiguration;
import org.tmforum.xml.tip.smi.ServiceConfiguration.ExtensionInfo;
import org.tmforum.xml.tip.smi.ServiceConfigurationFilter;
import org.tmforum.xml.tip.smi.SetExecutionStateRequest;
import org.tmforum.xml.tip.smi.SetExecutionStateResponse;
import org.tmforum.xml.tip.smi.SetServiceConfigurationRequest;
import org.tmforum.xml.tip.smi.SetServiceConfigurationResponse;
import org.tmforum.xml.tip.smi.State;
import org.tmforum.xml.tip.smi.UnregisterListenerRequest;
import org.tmforum.xml.tip.smi.UnregisterListenerResponse;
import rest2.HubEvent;

/**
 * REST Web Service
 * 
* @author pierregauthier
 */
@XmlSeeAlso(org.tmforum.smi.configurationdataex.ConfigData.class)
@Path("ServiceManagement")
public class GenericResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
        //JSONConfiguration.mapped().rootUnwrapping(false).build();
    }

    @GET
    @Path("/hubEvent")
    @Produces({"application/xml", "application/json"})
    public HubEvent getHubEvent() {
        HubEvent event = new HubEvent();
          ManagementReport managementReport = new ManagementReport();
        managementReport.setId("42");
        event.setEvent(managementReport);
       // event.setEvent(new String("data"));
        event.setEventType("ManagementReport");
        return event;
    }
    //real requests.

    @GET
    @Path("executionState")
    @Produces({
        "application/xml"
    })
    //get execution state REST 2
    public rest2.ExecutionState getExecutionStateRest2() {

        rest2.ExecutionState estate = new rest2.ExecutionState();
        estate.setExecutionState(ExecutionState.ACTIVE.value());
        return estate;

    }

    @GET
    @Path("executionState")
    @Produces({
        "application/json",})
    //get execution state REST 2
    public rest2.ExecutionState getExecutionStateRest2333() {

        rest2.ExecutionState estate = new rest2.ExecutionState();
        estate.setExecutionState(ExecutionState.ACTIVE.value());
        return estate;

    }

    //get execution state POST Version
    @PUT
    @Path("executionState")
    @Consumes({
        "application/json",
        "application/xml"
    })
    @Produces({
        "application/json",
        "application/xml"
    })
    //get execution state
    public rest2.ExecutionState putExecutionState(rest2.ExecutionState state) {


        return state;

    }

    @GET
    @Path("/GetServiceConfiguration")
    @Produces({
        "application/json",
        "application/xml"
    })
    public JAXBElement<GetServiceConfigurationResponse> getServiceConfiguration() {

        //    public JAXBElement<GetExecutionStateRequest> getExecutionState(@PathParam("request")
        // String body) {
        GetServiceConfigurationResponse mResp = new GetServiceConfigurationResponse();
        ServiceConfiguration serviceConfiguration = new ServiceConfiguration();
        ExtensionInfo extensionInfo = new ExtensionInfo();
        List<Object> list = extensionInfo.getAny();
        ConfigData cd = new ConfigData();
        NameValuePair nv = new NameValuePair();
        nv.setName("attribute");
        nv.setValue("value");
        cd.getValuePairs().add(nv);
        JAXBElement<ConfigData> f = new org.tmforum.smi.configurationdataex.ObjectFactory().createConfigdata(cd);
        // MetricFilter mfil = new MetricFilter();
        // JAXBElement<MetricFilter> f = new org.tmforum.xml.tip.smi.ObjectFactory().createMetricFilter(mfil);
        list.add(f);
        serviceConfiguration.setExtensionInfo(extensionInfo);
        // serviceConfiguration.setExtensionInfo(null);
        //create Sample Configuration Value
        //Check Type of Config Value
        mResp.setServiceConfiguration(serviceConfiguration);
        return new org.tmforum.xml.tip.smi.ObjectFactory().createGetServiceConfigurationResponse(mResp);

        //TODO implement this method
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    //following are the post commands
    //modify service/serviceconfiguration
    @POST
    @Path("/SetServiceConfiguration")
    @Consumes({
        "application/xml"
    })
    @Produces({
        "application/xml"
    })
    public JAXBElement<SetServiceConfigurationResponse> setServiceConfigurationPost(JAXBElement<SetServiceConfigurationRequest> req) {
        SetServiceConfigurationResponse mResp =
                new org.tmforum.xml.tip.smi.ObjectFactory().createSetServiceConfigurationResponse();


        //    public JAXBElement<GetExecutionStateRequest> getExecutionState(@PathParam("request")
        // String body) {

        return new org.tmforum.xml.tip.smi.ObjectFactory().createSetServiceConfigurationResponse(mResp);

        //TODO implement this method
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    @POST
    @Path("/SetServiceConfiguration")
    @Consumes({
        "application/json"
    })
    @Produces({
        "application/json"
    })
    public JAXBElement<SetServiceConfigurationResponse> setServiceConfigurationPostJSON(JAXBElement<SetServiceConfigurationRequest> req) {



        SetServiceConfigurationResponse mResp =
                new org.tmforum.xml.tip.smi.ObjectFactory().createSetServiceConfigurationResponse();


        //    public JAXBElement<GetExecutionStateRequest> getExecutionState(@PathParam("request")
        // String body) {

        return new org.tmforum.xml.tip.smi.ObjectFactory().createSetServiceConfigurationResponse(mResp);

        //TODO implement this method
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    @POST
    @Path("/serviceConfiguration")
    @Consumes({
        "application/xml"
    })
    @Produces({
        "application/xml"
    })
    public JAXBElement<ServiceConfiguration> getServiceConfigurationXMLRest2Post(JAXBElement<ServiceConfiguration> config) {

        return config;
    }

    @POST
    @Path("/serviceConfiguration")
    @Consumes({
        "application/json"
    })
    @Produces({
        "application/json"
    })
    public JAXBElement<ServiceConfiguration> getServiceConfigurationXMLRest222Post(JAXBElement<ServiceConfiguration> config) {

        return config;
    }

    @POST
    @Path("/SetExecutionState")
    @Consumes({
        "application/xml"
    })
    @Produces({
        "application/xml"
    })
    public JAXBElement<SetExecutionStateResponse> setExecutionStatePostXml(JAXBElement<SetExecutionStateRequest> req) {
        SetExecutionStateResponse mResp = new SetExecutionStateResponse();


        return new org.tmforum.xml.tip.smi.ObjectFactory().createSetExecutionStateResponse(mResp);

        //TODO implement this method
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    @POST
    @Path("/SetExecutionState")
    @Consumes({
        "application/json"
    })
    @Produces({
        "application/json"
    })
    public JAXBElement<SetExecutionStateResponse> setExecutionStatePostJSON(JAXBElement<SetExecutionStateRequest> req) {
        SetExecutionStateResponse mResp = new SetExecutionStateResponse();


        return new org.tmforum.xml.tip.smi.ObjectFactory().createSetExecutionStateResponse(mResp);

        //TODO implement this method
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    @POST
    @Path("/RegisterListener")
    @Consumes({
        "application/xml"
    })
    @Produces({
        "application/xml"
    })
    public JAXBElement<RegisterListenerResponse> registerListenerPost(JAXBElement<RegisterListenerRequest> req) {
        RegisterListenerResponse mResp = new RegisterListenerResponse();
        mResp.setResult(true);

        //    public JAXBElement<GetExecutionStateRequest> getExecutionState(@PathParam("request")
        // String body) {

        return new org.tmforum.xml.tip.smi.ObjectFactory().createRegisterListenerResponse(mResp);

        //TODO implement this method
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    @POST
    @Path("/RegisterListener")
    @Consumes({
        "application/json"
    })
    @Produces({
        "application/json"
    })
    public JAXBElement<RegisterListenerResponse> registerListenerPostJSON(JAXBElement<RegisterListenerRequest> req) {
        RegisterListenerResponse mResp = new RegisterListenerResponse();
        mResp.setResult(true);

       
        return new org.tmforum.xml.tip.smi.ObjectFactory().createRegisterListenerResponse(mResp);

        
    }

    @POST
    @Path("/UnregisterListener")
    @Consumes({
        "application/xml"
    })
    @Produces({
        "application/xml"
    })
    public JAXBElement<UnregisterListenerResponse> unregisterListenerPost(JAXBElement<UnregisterListenerRequest> req) {
        UnregisterListenerResponse mResp = new UnregisterListenerResponse();

        //    public JAXBElement<GetExecutionStateRequest> getExecutionState(@PathParam("request")
        // String body) {

        return new org.tmforum.xml.tip.smi.ObjectFactory().createUnregisterListenerResponse(mResp);

        //TODO implement this method
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    @POST
    @Path("/UnregisterListener")
    @Consumes({
        "application/json"
    })
    @Produces({
        "application/json"
    })
    public JAXBElement<UnregisterListenerResponse> unregisterListenerPostJSON(JAXBElement<UnregisterListenerRequest> req) {
        UnregisterListenerResponse mResp = new UnregisterListenerResponse();

        //    public JAXBElement<GetExecutionStateRequest> getExecutionState(@PathParam("request")
        // String body) {

        return new org.tmforum.xml.tip.smi.ObjectFactory().createUnregisterListenerResponse(mResp);

        //TODO implement this method
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    @POST
    @Path("/GetManagementReport")
    @Consumes({
        "application/xml"
    })
    @Produces({
        "application/xml"
    })
    public JAXBElement<GetManagementReportResponse> getManagementReportPost(JAXBElement<GetManagementReportRequest> req) {
        GetManagementReportResponse mRep = new org.tmforum.xml.tip.smi.ObjectFactory().createGetManagementReportResponse();
        ManagementReport managementReport = new ManagementReport();
        managementReport.setId("42");
        State state = new State();
        state.setExecutionState(ExecutionState.ACTIVE);
        state.setHealthState(HealthState.UNKNOWN);

        ArrayOfMetric arrayofmetrics = new ArrayOfMetric();
        managementReport.setMetrics(arrayofmetrics);
        List<Metric> list = arrayofmetrics.getItem();


        addMetrics(list);





        managementReport.setState(state);
        mRep.setManagementReport(managementReport);

        return new org.tmforum.xml.tip.smi.ObjectFactory().createGetManagementReportResponse(mRep);

        //TODO implement this method
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    @POST
    @Path("/GetManagementReport")
    @Consumes({
        "application/json"
    })
    @Produces({
        "application/json"
    })
    public JAXBElement<GetManagementReportResponse> getManagementReportPostJSON(JAXBElement<GetManagementReportRequest> req) {
        GetManagementReportResponse mRep = new org.tmforum.xml.tip.smi.ObjectFactory().createGetManagementReportResponse();
        ManagementReport managementReport = new ManagementReport();
        managementReport.setId("42");
        State state = new State();
        state.setExecutionState(ExecutionState.ACTIVE);
        state.setHealthState(HealthState.UNKNOWN);
        ArrayOfMetric arrayofmetrics = new ArrayOfMetric();
        managementReport.setMetrics(arrayofmetrics);
        List<Metric> list = arrayofmetrics.getItem();

        addMetrics(list);

        managementReport.setState(state);
        mRep.setManagementReport(managementReport);

        return new org.tmforum.xml.tip.smi.ObjectFactory().createGetManagementReportResponse(mRep);

        //TODO implement this method
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    @GET
    @Path("/managementReport/subResource")
    @Produces({
        "application/json", "application/xml"
    })
    public JAXBElement<ManagementReport> getManagementReportGetJSONToto(@PathParam("attributes") String as, @Context UriInfo info) {
        //MultivaluedMap<String, String> tt = info.getQueryParameters();
        URI cc = info.getRequestUri();
        String query = cc.getQuery();
        List<PathSegment> zz = info.getPathSegments();
        System.out.println(as);
        System.out.println(query);
        Iterator<PathSegment> it = zz.iterator();

        while (it.hasNext()) {
            PathSegment some = it.next();
            System.out.println(some.getPath());


        }

        //GetManagementReportResponse mRep = new org.tmforum.xml.tip.smi.ObjectFactory().createGetManagementReportResponse();
        ManagementReport managementReport = new ManagementReport();
        managementReport.setId("42");
        State state = new State();
        state.setExecutionState(ExecutionState.ACTIVE);
        state.setHealthState(HealthState.UNKNOWN);
        ArrayOfMetric arrayofmetrics = new ArrayOfMetric();
        managementReport.setMetrics(arrayofmetrics);
        List<Metric> list = arrayofmetrics.getItem();

        addMetrics(list);

        managementReport.setState(state);
        JAXBElement<ManagementReport> mr = new org.tmforum.xml.tip.smi.ObjectFactory().createManagementReport(managementReport);

        return mr;

    }

    @GET
    @Path("/managementReport/{attributes}")
    @Produces({
        "application/json", "application/xml"
    })
    public JAXBElement<ManagementReport> getManagementReportGetJSON(@PathParam("attributes") String as, @Context UriInfo info) {
        //MultivaluedMap<String, String> tt = info.getQueryParameters();
        URI cc = info.getRequestUri();
        String query = cc.getQuery();
        List<PathSegment> zz = info.getPathSegments();
        System.out.println(as);
        System.out.println(query);
        Iterator<PathSegment> it = zz.iterator();

        while (it.hasNext()) {
            PathSegment some = it.next();
            System.out.println(some.getPath());


        }

        //GetManagementReportResponse mRep = new org.tmforum.xml.tip.smi.ObjectFactory().createGetManagementReportResponse();
        ManagementReport managementReport = new ManagementReport();
        managementReport.setId("42");
        State state = new State();
        state.setExecutionState(ExecutionState.ACTIVE);
        state.setHealthState(HealthState.UNKNOWN);
        ArrayOfMetric arrayofmetrics = new ArrayOfMetric();
        managementReport.setMetrics(arrayofmetrics);
        List<Metric> list = arrayofmetrics.getItem();

        addMetrics(list);

        managementReport.setState(state);
        JAXBElement<ManagementReport> mr = new org.tmforum.xml.tip.smi.ObjectFactory().createManagementReport(managementReport);

        return mr;
        //mRep.setManagementReport(managementReport);

        //return new org.tmforum.xml.tip.smi.ObjectFactory().createGetManagementReportResponse(mRep);

        //TODO implement this method
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    @GET
    @Path("/managementReport")
    @Produces({
        "application/json", "application/xml"
    })
    public JAXBElement<ManagementReport> getManagementReportGetJSON(@Context UriInfo info) {
        //MultivaluedMap<String, String> tt = info.getQueryParameters();
        URI cc = info.getRequestUri();
        String query = cc.getQuery();
        List<PathSegment> zz = info.getPathSegments();
        // System.out.println(as);
        System.out.println(query);
        Iterator<PathSegment> it = zz.iterator();

        while (it.hasNext()) {
            PathSegment some = it.next();
            System.out.println(some.getPath());


        }

        //GetManagementReportResponse mRep = new org.tmforum.xml.tip.smi.ObjectFactory().createGetManagementReportResponse();
        ManagementReport managementReport = new ManagementReport();
        managementReport.setId("42");
        State state = new State();
        state.setExecutionState(ExecutionState.ACTIVE);
        state.setHealthState(HealthState.UNKNOWN);
        ArrayOfMetric arrayofmetrics = new ArrayOfMetric();
        managementReport.setMetrics(arrayofmetrics);
        List<Metric> list = arrayofmetrics.getItem();

        addMetrics(list);

        managementReport.setState(state);
        JAXBElement<ManagementReport> mr = new org.tmforum.xml.tip.smi.ObjectFactory().createManagementReport(managementReport);

        return mr;
        //mRep.setManagementReport(managementReport);

        //return new org.tmforum.xml.tip.smi.ObjectFactory().createGetManagementReportResponse(mRep);

        //TODO implement this method
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    @POST
    @Path("/GetServiceConfiguration")
    @Consumes({
        "application/xml"
    })
    @Produces({
        "application/xml"
    })
    public JAXBElement<GetServiceConfigurationResponse> getServiceConfigurationPost(JAXBElement<GetServiceConfigurationRequest> req) {

        //    public JAXBElement<GetExecutionStateRequest> getExecutionState(@PathParam("request")
        // String body) {
        GetServiceConfigurationResponse mResp = new GetServiceConfigurationResponse();
        ServiceConfiguration serviceConfiguration = new ServiceConfiguration();
        ExtensionInfo extensionInfo = new ExtensionInfo();
        List<Object> list = extensionInfo.getAny();
        ConfigData cd = new ConfigData();
        NameValuePair nv = new NameValuePair();
        nv.setName("attribute");
        nv.setValue("value");
        cd.getValuePairs().add(nv);
        JAXBElement<ConfigData> f = new org.tmforum.smi.configurationdataex.ObjectFactory().createConfigdata(cd);
        // MetricFilter mfil = new MetricFilter();
        // JAXBElement<MetricFilter> f = new org.tmforum.xml.tip.smi.ObjectFactory().createMetricFilter(mfil);
        list.add(f);
        serviceConfiguration.setExtensionInfo(extensionInfo);
        // serviceConfiguration.setExtensionInfo(null);
        //create Sample Configuration Value
        //Check Type of Config Value
        mResp.setServiceConfiguration(serviceConfiguration);
        JAXBElement<GetServiceConfigurationResponse> resp = new org.tmforum.xml.tip.smi.ObjectFactory().createGetServiceConfigurationResponse(mResp);
        System.out.println("GetServiceConfigurationResponse" + resp);
        return resp;
        //TODO implement this method
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    @POST
    @Path("/GetServiceConfiguration")
    @Consumes({
        "application/json"
    })
    @Produces({
        "application/json"
    })
    public JAXBElement<GetServiceConfigurationResponse> getServiceConfigurationPostJSON(JAXBElement<GetServiceConfigurationRequest> req) {

        //    public JAXBElement<GetExecutionStateRequest> getExecutionState(@PathParam("request")
        // String body) {
        GetServiceConfigurationResponse mResp = new GetServiceConfigurationResponse();
        ServiceConfiguration serviceConfiguration = new ServiceConfiguration();
        ExtensionInfo extensionInfo = new ExtensionInfo();
        List<Object> list = extensionInfo.getAny();
        ConfigData cd = new ConfigData();
        NameValuePair nv = new NameValuePair();
        nv.setName("attribute");
        nv.setValue("value");
        cd.getValuePairs().add(nv);
        JAXBElement<ConfigData> f = new org.tmforum.smi.configurationdataex.ObjectFactory().createConfigdata(cd);
        // MetricFilter mfil = new MetricFilter();
        // JAXBElement<MetricFilter> f = new org.tmforum.xml.tip.smi.ObjectFactory().createMetricFilter(mfil);
        list.add(f);
        serviceConfiguration.setExtensionInfo(extensionInfo);
        // serviceConfiguration.setExtensionInfo(null);
        //create Sample Configuration Value
        //Check Type of Config Value
        mResp.setServiceConfiguration(serviceConfiguration);
        return new org.tmforum.xml.tip.smi.ObjectFactory().createGetServiceConfigurationResponse(mResp);

        //TODO implement this method
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    @GET
    @Path("/serviceConfiguration")
    @Produces({
        "application/json", "application/xml"
    })
    public JAXBElement<ServiceConfiguration> getServiceConfigurationJSONRest2() {

        //    public JAXBElement<GetExecutionStateRequest> getExecutionState(@PathParam("request")
        // String body) {
        //GetServiceConfigurationResponse mResp = new GetServiceConfigurationResponse();
        ServiceConfiguration serviceConfiguration = new ServiceConfiguration();
        ExtensionInfo extensionInfo = new ExtensionInfo();
        List<Object> list = extensionInfo.getAny();
        ConfigData cd = new ConfigData();
        NameValuePair nv = new NameValuePair();
        nv.setName("attribute");
        nv.setValue("value");
        cd.getValuePairs().add(nv);
        JAXBElement<ConfigData> f = new org.tmforum.smi.configurationdataex.ObjectFactory().createConfigdata(cd);
        // MetricFilter mfil = new MetricFilter();
        // JAXBElement<MetricFilter> f = new org.tmforum.xml.tip.smi.ObjectFactory().createMetricFilter(mfil);
        list.add(f);
        serviceConfiguration.setExtensionInfo(extensionInfo);
        // serviceConfiguration.setExtensionInfo(null);
        //create Sample Configuration Value
        //Check Type of Config Value
        //mResp.setServiceConfiguration(serviceConfiguration);
        // return new org.tmforum.xml.tip.smi.ObjectFactory().createGetServiceConfigurationResponse(mResp);
        return new org.tmforum.xml.tip.smi.ObjectFactory().createServiceConfiguration(serviceConfiguration);
        //TODO implement this method
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    //get execution state
    @POST
    @Path("/GetExecutionState")
    @Consumes({
        "application/xml"
    })
    @Produces({
        "application/xml"
    })
    public JAXBElement<GetExecutionStateResponse> getExecutionStatePost(JAXBElement<GetExecutionStateRequest> req) {
        ObjectFactory ofac = new org.tmforum.xml.tip.smi.ObjectFactory();
        GetExecutionStateResponse executionStateReq = new GetExecutionStateResponse();
        executionStateReq.setExecutionState(ExecutionState.ACTIVE);

        JAXBElement<GetExecutionStateResponse> resp = ofac.createGetExecutionStateResponse(executionStateReq);



        return resp;

        //TODO implement this method
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    @POST
    @Path("/GetExecutionState")
    @Consumes({
        "application/json"
    })
    @Produces({
        "application/json"
    })
    public JAXBElement<GetExecutionStateResponse> getExecutionStatePostJSON(JAXBElement<GetExecutionStateRequest> req) {
        ObjectFactory ofac = new org.tmforum.xml.tip.smi.ObjectFactory();
        GetExecutionStateResponse executionStateReq = new GetExecutionStateResponse();
        executionStateReq.setExecutionState(ExecutionState.ACTIVE);
        JAXBElement<GetExecutionStateResponse> resp = ofac.createGetExecutionStateResponse(executionStateReq);



        return resp;

        //TODO implement this method
        //throw new UnsupportedOperationException("Not implemented yet.");
    }

    public XMLGregorianCalendar getXMLGregorianCalendarNow()
            throws DatatypeConfigurationException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
        XMLGregorianCalendar now =
                datatypeFactory.newXMLGregorianCalendar(gregorianCalendar);
        return now;
    }

    private void addMetrics(List<Metric> list) {


        /* 
         * "PercentageofLostPackets"
         * 76e27e2b-644e-11e2-8ea1-5c260a86d1e4	CEM - VoIP Voice	Lost Packet Rate	Customer Service : Sessionized Service : Voice : VoIP Voice	"SIP, RTP, H.323"	Percentage of Lost Packets	KPI (Mean)
         * 
         * "PercentageofDuplicatePackets"
         * "PercentageofOOSPackets"
         * "Totaltime(ms)ofContentStreaming"
         * "NumberofStreamingFailures"
         * "TimeofSession(ms)whereJitterwasoutsideacceptablequality"
         * "Successpercentageforplayrequests"
         */

        Metric metric = getPercentageofLostPacketsMetric();
        list.add(metric);
        metric = getPercentageofDuplicatePacketsMetric();
        list.add(metric);
        metric = getPercentageofOOSPacketsMetric();
        list.add(metric);
        metric = getTotaltimeofContentStreamingMetric();
        list.add(metric);
        metric = getNumberofStreamingFailuresMetric();
        list.add(metric);
        metric = getTimeofSessionwhereJitterwasoutMetric();
        list.add(metric);
        metric = getSuccesspercentageforplayrequestsMetric();
        list.add(metric);





    }

    public Metric getPercentageofLostPacketsMetric() {

        Metric metric = new Metric();
        String cid = null;

        //Code as in UUID 
        String code = new String("76e27e2b-644e-11e2-8ea1-5c260a86d1e4");
        metric.setCode(code);


        XMLGregorianCalendar dt = null;
        try {
            dt = getXMLGregorianCalendarNow();
        } catch (DatatypeConfigurationException ex) {
            System.out.println("getting mettric exception ");
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        metric.setDateTime(dt);

        String val = new String("20");
        metric.setValue(val);

        return metric;

    }

    public Metric getPercentageofDuplicatePacketsMetric() {

        Metric metric = new Metric();
        String cid = null;

        //Code as in UUID 
        String code = new String("76e9e2e9-644e-11e2-8ea1-5c260a86d1e4");
        metric.setCode(code);


        XMLGregorianCalendar dt = null;
        try {
            dt = getXMLGregorianCalendarNow();
        } catch (DatatypeConfigurationException ex) {
            System.out.println("getting mettric exception ");
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        metric.setDateTime(dt);

        String val = new String("10");
        metric.setValue(val);

        return metric;

    }

    public Metric getPercentageofOOSPacketsMetric() {

        Metric metric = new Metric();
        String cid = null;

        //Code as in UUID 
        String code = new String("76f2b3f1-644e-11e2-8ea1-5c260a86d1e4");
        metric.setCode(code);


        XMLGregorianCalendar dt = null;
        try {
            dt = getXMLGregorianCalendarNow();
        } catch (DatatypeConfigurationException ex) {
            System.out.println("getting mettric exception ");
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        metric.setDateTime(dt);

        String val = new String("5");
        metric.setValue(val);

        return metric;

    }

    public Metric getTotaltimeofContentStreamingMetric() {

        Metric metric = new Metric();
        String cid = null;

        //Code as in UUID 
        String code = new String("7987c7bc-644e-11e2-8ea1-5c260a86d1e4");
        metric.setCode(code);


        XMLGregorianCalendar dt = null;
        try {
            dt = getXMLGregorianCalendarNow();
        } catch (DatatypeConfigurationException ex) {
            System.out.println("getting mettric exception ");
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        metric.setDateTime(dt);

        String val = new String("60000");
        metric.setValue(val);

        return metric;

    }

    public Metric getNumberofStreamingFailuresMetric() {

        Metric metric = new Metric();
        String cid = null;

        //Code as in UUID 
        String code = new String("7c7d6e22-644e-11e2-8ea1-5c260a86d1e4");
        metric.setCode(code);


        XMLGregorianCalendar dt = null;
        try {
            dt = getXMLGregorianCalendarNow();
        } catch (DatatypeConfigurationException ex) {
            System.out.println("getting mettric exception ");
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        metric.setDateTime(dt);

        String val = new String("12");
        metric.setValue(val);

        return metric;

    }

    public Metric getTimeofSessionwhereJitterwasoutMetric() {

        Metric metric = new Metric();
        String cid = null;

        //Code as in UUID 
        String code = new String("7c98ea37-644e-11e2-8ea1-5c260a86d1e4");
        metric.setCode(code);


        XMLGregorianCalendar dt = null;
        try {
            dt = getXMLGregorianCalendarNow();
        } catch (DatatypeConfigurationException ex) {
            System.out.println("getting mettric exception ");
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        metric.setDateTime(dt);

        String val = new String("70000");
        metric.setValue(val);

        return metric;

    }

    public Metric getSuccesspercentageforplayrequestsMetric() {

        Metric metric = new Metric();
        String cid = null;

        //Code as in UUID 
        String code = new String("7dd589b2-644e-11e2-8ea1-5c260a86d1e4");
        metric.setCode(code);


        XMLGregorianCalendar dt = null;
        try {
            dt = getXMLGregorianCalendarNow();
        } catch (DatatypeConfigurationException ex) {
            System.out.println("getting mettric exception ");
            Logger.getLogger(GenericResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        metric.setDateTime(dt);

        String val = new String("89");
        metric.setValue(val);

        return metric;
    }
}
