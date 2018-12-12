package org.zero.hessian.server.config;

import com.caucho.hessian.io.SerializerFactory;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.web.util.NestedServletException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yezhaoxing
 * @since 2018/12/12
 * @description 自定义HessianServiceExporter, 拓展handleRequest方法, 增加权限校验
 */
public class CustomerHessianServiceExporter extends HessianServiceExporter {

    private static final String TRUE_AUTHORIZATION = "Basic dXNlcjpwYXNzd29yZA==";

    static CustomerHessianServiceExporter instance() {
        CustomerHessianServiceExporter exporter = new CustomerHessianServiceExporter();// 自定义的HessianServiceExporter
        SerializerFactory serializerFactory = new SerializerFactory();
        serializerFactory.addFactory(new MyAbstractSerializerFactory());
        exporter.setSerializerFactory(serializerFactory);// 重新定义序列化方法,不然BigDecimal有bug
        return exporter;
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 做权限校验
        String authorization = request.getHeader("Authorization");
        if (!TRUE_AUTHORIZATION.equals(authorization)) {
            throw new NestedServletException("auth fail");
        }
        super.handleRequest(request, response);
    }
}
