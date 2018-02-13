package raven.serializer.withMsgpack;

import org.msgpack.MessagePack;
import org.msgpack.template.TemplateRegistry;

/**
 *
 *
 * @author yi.liang
 * @since JDK1.8
 * created by 2018/2/12 12:00:00
 */
public class CustomMessagePack extends MessagePack {

    /**
     *
     * @param registry
     */
    public CustomMessagePack(TemplateRegistry registry){
        super(registry);
    }

}
