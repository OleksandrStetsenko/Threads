package home.stetsenko.egg_chichen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChickenVoice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChickenVoice.class);

    private static Thread mAnotherOpinion;	//Побочный поток

    public static void main(String[] args)
    {
        EggVoice eggVoice = new EggVoice();	//Создание потока
        mAnotherOpinion = new Thread(eggVoice);
        LOGGER.info("Спор начат...");
        mAnotherOpinion.start(); 			//Запуск потока

        for(int i = 0; i < 5; i++)
        {
            try{
                Thread.sleep(1000);		//Приостанавливает поток на 1 секунду
            }catch(InterruptedException e){}

            LOGGER.info("курица!");
        }

        //Слово «курица» сказано 5 раз
        if(mAnotherOpinion.isAlive())	//Если оппонент еще не сказал последнее слово
        {
            try{
                mAnotherOpinion.join();	//Подождать пока оппонент закончит высказываться.
            }catch(InterruptedException e){}

            LOGGER.info("Первым появилось яйцо!");
        }
        else	//если оппонент уже закончил высказываться
        {
            LOGGER.info("Первой появилась курица!");
        }
        LOGGER.info("Спор закончен!");
    }

}
