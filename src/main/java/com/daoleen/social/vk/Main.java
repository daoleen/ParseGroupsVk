package com.daoleen.social.vk;

import org.apache.log4j.Logger;

/**
 * Created by alex on 3/22/14.
 */
public class Main {
    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        VkParser parser = new VkParser();
        if(args.length > 0) {
            setArguments(args);
        }

        int universityStart=255, universityEnd=1500;
        logger.info(String.format("Parsing universities between %d and %d", universityStart, universityEnd));
        for(int i = universityStart; i < universityEnd; i++) {
            logger.info("Parsing university #"+i+" of "+universityEnd);
            Config.getInstance().setUniversity(i);
            Config.getInstance().setBasename("university-"+i);
            parser.getPeoplesProfiles();
        }
        logger.info("Done");
    }

    private static void setArguments(String[] args) {
        Config config = Config.getInstance();

        for(String arg : args) {
            if(arg.length() > 0) {
                String[] prop = arg.split("=");

                if(prop.length != 2) {
                    System.out.println("Неверно переданы параметры");
                    System.exit(0);
                }

                switch (prop[0]) {
                    case "--offset":
                        config.setOffset(Integer.parseInt(prop[1]));
                        break;

                    case "--count":
                        config.setCount(Integer.parseInt(prop[1]));
                        break;

                    case "--fields":
                        config.setFields(prop[1]);
                        break;

                    case "--university":
                        config.setUniversity(Integer.parseInt(prop[1]));
                        break;

                    case "--access-token":
                        config.setAccessToken(prop[1]);
                        break;

                    case "--basename":
                        config.setBasename(prop[1]);
                        break;

                    case "--recordsPerFile":
                        config.setRecordsPerFile(Integer.parseInt(prop[1]));
                        break;
                }
            }
        }
    }
}
