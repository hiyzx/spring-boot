package org.zero.hessian.server.config;

import com.caucho.hessian.io.SerializerFactory;
import com.caucho.hessian.server.HessianServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

/**
 * @author yezhaoxing
 * @since 2018/12/12
 */
@WebServlet(name = "myHessianServlet", urlPatterns = "/", loadOnStartup = 0)
public class MyHessianServlet extends HessianServlet {

    private SerializerFactory serializerFactory;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        serializerFactory = super.getSerializerFactory();
        serializerFactory.addFactory(new MyAbstractSerializerFactory());
    }

    @Override
    public SerializerFactory getSerializerFactory() {
        return serializerFactory;
    }
}