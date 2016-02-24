package home.stetsenko.incrementator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Incrementator extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(Incrementator.class);

    /*
    * В объявлении переменных mIsIncrement и mFinish было использовано ключевое слово volatile (изменчивый, не постоянный).
    * Его необходимо использовать для переменных, которые используются разными потоками.
    * Это связано с тем, что значение переменной, объявленной без volatile, может кэшироваться отдельно для каждого потока,
    * и значение из этого кэша может различаться для каждого из них. Объявление переменной с ключевым
    * словом volatile отключает для неё такое кэширование и все запросы к переменной будут направляться непосредственно в память.
    * */
    private volatile boolean mIsIncrement = true;

    //Меняет действие на противоположное
    public void changeAction() {
        mIsIncrement = !mIsIncrement;
    }


    @Override
    public void run() {
        do {
            if (!Thread.interrupted())    //Проверка на необходимость завершения
            {
                if (mIsIncrement) {
                    Program.mValue++;    //Инкремент
                } else {
                    Program.mValue--;    //Декремент
                }
                //Вывод текущего значения переменной
                LOGGER.info(Program.mValue + " ");
            } else {
                LOGGER.debug("Incremenator is finished");
                return;        //Завершение потока
            }

            try {
                Thread.sleep(1000);        //Приостановка потока на 1 сек.
            } catch (InterruptedException e) {
                LOGGER.error("Incremenator is finished due to interruption ");
                return;	//Завершение потока после прерывания
            }
        }
        while (true);
    }

}
