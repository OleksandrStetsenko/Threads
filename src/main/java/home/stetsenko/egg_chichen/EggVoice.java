package home.stetsenko.egg_chichen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Thread.sleep;

public class EggVoice implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(EggVoice.class);

    @Override
    public void run()
    {
        for(int i = 0; i < 5; i++)
        {
            try{
                sleep(1000);		//Sleep 1 sec
            }catch(InterruptedException e){}

            LOGGER.info("яйцо!");
        }
        //Слово «яйцо» сказано 5 раз;
    }

}
