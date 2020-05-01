package wr1ttenyu.f1nal.study.project.archetype.util.common.exception.assertion;


import wr1ttenyu.f1nal.study.project.archetype.util.common.exception.ArgumentException;
import wr1ttenyu.f1nal.study.project.archetype.util.common.exception.BaseException;
import wr1ttenyu.f1nal.study.project.archetype.util.common.exception.IResponseEnum;

import java.text.MessageFormat;

public interface CommonExceptionAssert extends IResponseEnum, Assert {

    @Override
    default BaseException newException(Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);

        return new BaseException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = MessageFormat.format(this.getMessage(), args);

        return new BaseException(this, args, msg, t);
    }

}
