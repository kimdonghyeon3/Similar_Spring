package com.ll.exam.spring;

import com.ll.exam.App;
import com.ll.exam.db.MyDb;
import com.ll.exam.util.Rq;
import com.ll.exam.util.Util;
import com.ll.exam.annotation.Controller;
import com.ll.exam.annotation.GetMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ControllerManager {
    private static Map<String, RouteInfo> routeInfos;

    static {
        routeInfos = new HashMap<>();

        scanMappings();
    }

    private static void scanMappings() {
        Reflections ref = new Reflections(App.BASE_PACKAGE_PATH);
        for (Class<?> controllerCls : ref.getTypesAnnotatedWith(Controller.class)) {
            Method[] methods = controllerCls.getDeclaredMethods();

            for (Method method : methods) {
                GetMapping getMapping = method.getAnnotation(GetMapping.class);

                String httpMethod = null;
                String path = null;

                if (getMapping != null) {
                    path = getMapping.value();
                    httpMethod = "GET";
                }

                if (path != null && httpMethod != null) {
                    String actionPath = Util.str.beforeFrom(path, "/", 4);

                    String key = httpMethod + "___" + actionPath;

                    routeInfos.put(key, new RouteInfo(path, actionPath, controllerCls, method));
                }
            }
        }
    }

    public static void runAction(HttpServletRequest req, HttpServletResponse resp) {
        Rq rq = new Rq(req, resp);

        String routeMethod = rq.getRouteMethod();
        String actionPath = rq.getActionPath();

        String mappingKey = routeMethod + "___" + actionPath;

        System.out.println(mappingKey);
        System.out.println(routeInfos.keySet());

        boolean contains = routeInfos.containsKey(mappingKey);

        if (contains == false) {
            rq.println("?????? ????????? ???????????? ????????????.");
            return;
        }

        RouteInfo routeInfo = routeInfos.get(mappingKey);
        rq.setRouteInfo(routeInfo);

        runAction(rq);
    }

    private static void runAction(Rq rq) {
        RouteInfo routeInfo = rq.getRouteInfo();
        Class controllerCls = routeInfo.getControllerCls();
        Method actionMethod = routeInfo.getMethod();

        Object controllerObj = Container.getObj(controllerCls);

        try {
            actionMethod.invoke(controllerObj, rq);
        } catch (IllegalAccessException e) {
            rq.println("??????????????? ?????????????????????.");
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
            //rq.println("??????????????? ?????????????????????.");
        } finally {
            MyDb myDb = Container.getObj(MyDb.class);
            myDb.closeConnection(); // ?????? ???????????? ????????? ???????????? ?????????.
            // ????????? ???????????? ???
            // ??? ????????????, DB ????????? ?????????
        }
    }

    public static void init() {

    }

    public static Map<String, RouteInfo> getRouteInfosForTest() {
        return routeInfos;
    }
}