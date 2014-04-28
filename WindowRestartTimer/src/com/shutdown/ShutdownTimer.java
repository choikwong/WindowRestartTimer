package com.shutdown;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimerTask;

public class ShutdownTimer extends TimerTask {

    private String shuttime;
    private String[] shutday;
    private String[] time;
    private String[] dayPriorityList;
    private boolean normal;

    public ShutdownTimer() {
	updateShutdwonTime();
    }

    public void updateShutdwonTime() {
	shuttime = null;
	shutday = null;
	normal = true;
	time = Calendar.getInstance().getTime().toString().split(" ");
	generateDayPriorityList();
	if (shuttime == null || shutday == null) {
	    ArrayList<String> tickday = new ArrayList<String>();
	    for (Object o : ShutdownProgram.GETDATASET().getAllShutdown_Days()
		    .keySet()) {
		if ((boolean) ShutdownProgram.GETDATASET()
			.getAllShutdown_Days().get(o)) {
		    tickday.add(o.toString());
		}
	    }
	    for (int i = 0; i < dayPriorityList.length; i++) {
		if (shuttime == null || shutday == null) {
		    for (String d : tickday) {
			if (dayPriorityList[i].equals(d)) {
			    if (i == 0) {
				String[] ts0 = time[3].split(":");
				String[] ts1 = ShutdownProgram.GETDATASET()
					.getAllShutdownTime().get(d).toString()
					.split(":");
				if ((Integer.parseInt(ts1[0])) > (Integer
					.parseInt(ts0[0]))) {
				    shuttime = d;
				    shutday = ts1;
				    break;
				} else if ((Integer.parseInt(ts1[0])) == (Integer
					.parseInt(ts0[0]))) {
				    if ((Integer.parseInt(ts1[1])) > (Integer
					    .parseInt(ts0[1]))) {
					shuttime = d;
					shutday = ts1;
					break;
				    } else if ((Integer.parseInt(ts1[1])) == (Integer
					    .parseInt(ts0[1]))) {
					if ((Integer.parseInt(ts1[2])) > (Integer
						.parseInt(ts0[2]))) {
					    shuttime = d;
					    shutday = ts1;
					    break;
					}

				    }

				}

			    } else {
				shuttime = d;
				shutday = ShutdownProgram.GETDATASET()
					.getAllShutdownTime().get(d).toString()
					.split(":");
				break;

			    }
			}
		    }
		}
	    }
	    if ((shuttime == null || shutday == null) && !tickday.isEmpty()) {
		shuttime = tickday.get(0);
		shutday = ShutdownProgram.GETDATASET().getAllShutdownTime()
			.get(tickday.get(0)).toString().split(":");
		normal = false;
	    }
	}
    }

    private String escapeTime() {
	int a = 0;
	String escapetime = "";
	int[] et = new int[3];
	if (normal) {
	    for (int i = 0; i < dayPriorityList.length; i++) {
		if (dayPriorityList[i].equals(shuttime)) {
		    a = i;
		    break;
		}
	    }
	} else {
	    a = 7;
	}
	if (a == 0 && !(shutday == null)) {
	    String[] currenttime = time[3].split(":");
	    for (int i = 0; i < currenttime.length; i++) {
		
		int tempnum = Integer.parseInt(shutday[i])
			- Integer.parseInt(currenttime[i]);
		
		if (tempnum <= 0 && !(tempnum==0 && i == 0)) {
		    tempnum = 60 + tempnum;
		    if(i ==1){
			et[i-1] -= 1;
		    }
		}
		if (i == 1 && !(tempnum==0)) {
		    tempnum -= 1;
		}
		et[i] = tempnum;
		
	    }
	    for(int i = 0; i < et.length; i++){
		if (String.valueOf(et[i]).length() == 1) {
		    escapetime += "0" + String.valueOf(et[i]);
		} else {
		    escapetime += String.valueOf(et[i]);
		}
		if (i >= 0 && i < 2) {
		    escapetime += ":";
		}
	    }
	} else if (!(shutday == null)) {
	    String[] currenttime = time[3].split(":");
	    int[] intTime = new int[3];
	    for (int i = 0; i < currenttime.length; i++) {
		if (i == 0) {
		    int tempnum = 23 - Integer.parseInt(currenttime[i]);
		    tempnum += Integer.parseInt(shutday[i]);
		    if (i == 0) {
			tempnum += ((a - 1) * 24);
		    }
		    intTime[i] = tempnum;

		} else {
		    int tempnum = 60 - Integer.parseInt(currenttime[i]);
		    tempnum += Integer.parseInt(shutday[i]);
		    if (tempnum >= 60) {
			tempnum = tempnum - 60;
			intTime[i - 1] += 1;
		    }
		    intTime[i] = tempnum;
		}
	    }
	    for (int i = 0; i < intTime.length; i++) {
		if (String.valueOf(intTime[i]).length() == 1) {
		    escapetime += "0" + String.valueOf(intTime[i]);
		} else {
		    escapetime += String.valueOf(intTime[i]);
		}
		if (i >= 0 && i < 2) {
		    escapetime += ":";
		}
	    }
	} else {
	    escapetime = "??:??:??";
	}
	return escapetime;
    }

    private void generateDayPriorityList() {
	time = Calendar.getInstance().getTime().toString().split(" ");
	switch (time[0]) {
	case "Mon":
	    dayPriorityList = new String[] { ShutdownTask.MONDAY,
		    ShutdownTask.TUESDAY, ShutdownTask.WEDNESDAY,
		    ShutdownTask.THURDAY, ShutdownTask.FRIDAY,
		    ShutdownTask.SATURDAY, ShutdownTask.SUNDAY };
	    break;
	case "Tue":
	    dayPriorityList = new String[] { ShutdownTask.TUESDAY,
		    ShutdownTask.WEDNESDAY, ShutdownTask.THURDAY,
		    ShutdownTask.FRIDAY, ShutdownTask.SATURDAY,
		    ShutdownTask.SUNDAY, ShutdownTask.MONDAY };
	    break;
	case "Wed":
	    dayPriorityList = new String[] { ShutdownTask.WEDNESDAY,
		    ShutdownTask.THURDAY, ShutdownTask.FRIDAY,
		    ShutdownTask.SATURDAY, ShutdownTask.SUNDAY,
		    ShutdownTask.MONDAY, ShutdownTask.TUESDAY };
	    break;
	case "Thu":
	    dayPriorityList = new String[] { ShutdownTask.THURDAY,
		    ShutdownTask.FRIDAY, ShutdownTask.SATURDAY,
		    ShutdownTask.SUNDAY, ShutdownTask.MONDAY,
		    ShutdownTask.TUESDAY, ShutdownTask.WEDNESDAY };
	    break;
	case "Fri":
	    dayPriorityList = new String[] { ShutdownTask.FRIDAY,
		    ShutdownTask.SATURDAY, ShutdownTask.SUNDAY,
		    ShutdownTask.MONDAY, ShutdownTask.TUESDAY,
		    ShutdownTask.WEDNESDAY, ShutdownTask.THURDAY };
	    break;
	case "Sat":
	    dayPriorityList = new String[] { ShutdownTask.SATURDAY,
		    ShutdownTask.SUNDAY, ShutdownTask.MONDAY,
		    ShutdownTask.TUESDAY, ShutdownTask.WEDNESDAY,
		    ShutdownTask.THURDAY, ShutdownTask.FRIDAY };
	    break;
	case "Sun":
	    dayPriorityList = new String[] { ShutdownTask.SUNDAY,
		    ShutdownTask.MONDAY, ShutdownTask.TUESDAY,
		    ShutdownTask.WEDNESDAY, ShutdownTask.THURDAY,
		    ShutdownTask.FRIDAY, ShutdownTask.SATURDAY };
	    break;
	default:
	    dayPriorityList = new String[] { ShutdownTask.MONDAY,
		    ShutdownTask.TUESDAY, ShutdownTask.WEDNESDAY,
		    ShutdownTask.THURDAY, ShutdownTask.FRIDAY,
		    ShutdownTask.SATURDAY, ShutdownTask.SUNDAY };

	}

    }

    public String getShutTimeOnLabel() {
	if (shuttime == null || shutday[0] == null || shutday[1] == null
		|| shutday[2] == null) {
	    return "Not Set";
	} else {
	    return shuttime + " " + shutday[0] + ":" + shutday[1] + ":"
		    + shutday[2];
	}
    }

    @Override
    public void run() {
	time = Calendar.getInstance().getTime().toString().split(" ");
	if (time[0].equals(shuttime)) {
	    String[] ts0 = time[3].split(":");
	    if ((Integer.parseInt(shutday[0])) == (Integer.parseInt(ts0[0]))) {
		if ((Integer.parseInt(shutday[1])) == (Integer.parseInt(ts0[1]))) {
		    if ((Integer.parseInt(ts0[2]))
			    - (Integer.parseInt(shutday[2])) <= 10) {
			ShutdownProgram.STOPTIMER();
			try {
			    Runtime.getRuntime().exec("shutdown -s -f");
			} catch (IOException e) {
			    // TODO Auto-generated catch block
			    e.printStackTrace();
			}
		    }
		}
	    }
	}
	ShutdownProgram.GETSHUTDOWNINTERFACE().updateEscapeTime(escapeTime());
    }
}
