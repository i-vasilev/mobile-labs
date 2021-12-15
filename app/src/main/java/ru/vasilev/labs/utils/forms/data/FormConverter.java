package ru.vasilev.labs.utils.forms.data;

import android.content.Context;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

import ru.vasilev.labs.R;

public class FormConverter {
    private final Context context;

    public FormConverter(Context context) {
        this.context = context;
    }

    public String getString(Form form) {
        StringBuilder data = new StringBuilder();
        data.append(form.toString())
            .append(System.getProperty("line.separator"))
            .append(getString(R.string.education))
            .append(getString(form.getEducation()
                                  .getId()))
            .append(System.getProperty("line.separator"))
            .append(getString(R.string.expectedBranchOfWork))
            .append(getString(form.getIndustry()
                                  .getId()))
            .append(System.getProperty("line.separator"))
            .append(getString(R.string.experience))
            .append(getString(form.getExperience()
                                  .getId()))
            .append(System.getProperty("line.separator"))
            .append(getString(R.string.expectedPayLevel))
            .append(form.getDesiredPay())
            .append(System.getProperty("line.separator"))
            .append(getString(R.string.typeEmployment))
            .append(getEmploymentTypes(form))
            .append(System.getProperty("line.separator"));
        if (form.isHasPhysicalLimitations()) {
            data.append(getString(R.string.hasPhysicalRestrictions));
        } else {
            data.append(getString(R.string.noPhysicalRestrictions));
        }
        data.append(System.getProperty("line.separator"));
        if (form.isHasCriminalRecord()) {
            data.append(getString(R.string.hasCriminal));
        } else {
            data.append(getString(R.string.noCriminal));
        }
        if (form.getAdditionalInformation() != null) {
            data.append(System.getProperty("line.separator"))
                .append(getString(R.string.additionalInformation))
                .append(form.getAdditionalInformation());
        }
        return data.toString();
    }

    private String getEmploymentTypes(Form form) {
        List<String> types = new ArrayList<>();
        if (form.isFullTime()) types.add(getString(R.string.fullTime));
        if (form.isPartTime()) types.add(getString(R.string.partTime));
        if (form.isInternship()) types.add(getString(R.string.internship));
        if (form.isOnetime()) types.add(getString(R.string.oneTime));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return String.join(", ", types);
        }
        return null;
    }

    private String getString(int id) {
        return context.getString(id);
    }
}
