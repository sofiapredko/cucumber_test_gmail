package com.sofia.decorators.customdecotator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.sofia.decorators.IElement;
import com.sofia.decorators.WrapperFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class LocatingCustomElementListHandler implements InvocationHandler {
    private final ElementLocator locator;
    private final Class<IElement> clazz;

    public LocatingCustomElementListHandler(ElementLocator locator, Class<IElement> clazz) {
        this.locator = locator;
        this.clazz = clazz;
    }

    /**
     * Знаходить список  WebElement і опрацьовує кожен його елемент
     * і повертає новий список з елементами кастомного класу
     */
    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {

        List<WebElement> elements = locator.findElements();
        List<IElement> customs = new ArrayList<IElement>();

        for (WebElement element : elements) {
            customs.add(WrapperFactory.createInstance(clazz, element));
        }
        try {
            return method.invoke(customs, objects);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }
}