package StopWatch;

//========== ?????? ================================================
    public class ChangeMilliSocendToTimeC
    {
        private int AN_HOUR = 60 * 60 * 1000;
        private int A_MINUTE = 60 * 1000;
        private int A_SOCEND = 1000;

        private long lngMilliSeconds;

        ChangeMilliSocendToTimeC()
        {


        }

        ChangeMilliSocendToTimeC(long lngMilliSecond)
        {
            lngMilliSeconds = lngMilliSecond;
        }

        public String getTime(long lngMilliSecond)
        {
            lngMilliSeconds = lngMilliSecond;

            return getTime();
        }


        public String getTime()
        {
            int intHours, intMinutes, intSeconds, intMilliseconds;
            String strHours, strMinutes, strSeconds, strMilliseconds;
            strHours = "00";
            strMinutes = "00";
            strSeconds = "00";
            strMilliseconds = "000";

            intHours = (int)(lngMilliSeconds / AN_HOUR);
            if(intHours < 10)
            {
                strHours = "0" + String.valueOf(intHours);
            }
            else
            {
                 strHours = String.valueOf(intHours);
            }
            lngMilliSeconds = lngMilliSeconds - (intHours * AN_HOUR);
            intMinutes = (int)(lngMilliSeconds / A_MINUTE);
            if(intMinutes < 10)
            {
                strMinutes = "0" + String.valueOf(intMinutes);
            }
            lngMilliSeconds = lngMilliSeconds - (intMinutes * A_MINUTE);
            intSeconds = (int)(lngMilliSeconds / A_SOCEND);
            if(intSeconds < 10)
            {
                strSeconds = "0" + String.valueOf(intSeconds);
            }
            else
            {
                strSeconds = String.valueOf(intSeconds);
            }
            lngMilliSeconds = lngMilliSeconds - (intSeconds * A_SOCEND);
            intMilliseconds = (int)lngMilliSeconds;
            if(intMilliseconds < 100)
            {
                strMilliseconds = "0" + String.valueOf(intMilliseconds);
            }
            else
            {
                strMilliseconds = String.valueOf(intMilliseconds);
            }

            return strHours + ":" + strMinutes + ":" + strSeconds + ":" + strMilliseconds;

        }
    }
