package com.intellij.tapestry.intellij.core.java;

import com.intellij.psi.PsiType;
import com.intellij.tapestry.core.java.IJavaType;
import com.intellij.tapestry.core.log.Logger;
import com.intellij.tapestry.core.log.LoggerFactory;
import org.jetbrains.annotations.Nullable;

public abstract class IntellijJavaType implements IJavaType {

    private static final Logger _logger = LoggerFactory.getInstance().getLogger(IntellijJavaType.class);

    public boolean isAssignableFrom(@Nullable IJavaType type) {
        if (type == null)
            return false;

        if (getUnderlyingObject() == null || !(getUnderlyingObject() instanceof PsiType)) {
            _logger.warn("The type \"" + getName() + "\" didn't have a valid underlying object so correct usage of the type wasn't possible.");

            return false;
        }

        if (!(type.getUnderlyingObject() instanceof PsiType)) {
            _logger.warn("The type \"" + type.getName() + "\" didn't have a valid underlying object so correct execution of isAssignableFrom wasn't possible.");

            return false;
        }

        return ((PsiType) getUnderlyingObject()).isAssignableFrom((PsiType) type.getUnderlyingObject());
    }
}
