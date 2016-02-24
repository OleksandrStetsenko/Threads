package home.stetsenko.incrementator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Program {

    private static final Logger LOGGER = LoggerFactory.getLogger(Program.class);

    //Переменая, которой оперирует инкременатор
    public static int mValue = 0;

    static Incrementator mInc;	//Объект побочного потока

    public static void main(String[] args)
    {
        mInc = new Incrementator();	//Создание потока

        LOGGER.info("Значение = ");

        mInc.start();	//Запуск потока

        //Троекратное изменение действия инкременатора
        //с интервалом в i*2 секунд
        for(int i = 1; i <= 3; i++)
        {
            try{
                Thread.sleep(i * 2 * 1000); //Ожидание в течении i*2 сек.
            }catch(InterruptedException e){
                LOGGER.error("Interrupted", e);
            }

            mInc.changeAction();	//Переключение действия
        }

        mInc.interrupt();	//Инициация завершения побочного потока

        LOGGER.debug("main thread is finished");
    }

}
