// Copyright 2000-2020 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.jetbrains.astro.codeInsight

import com.intellij.codeInsight.daemon.impl.analysis.XmlUnboundNsPrefixInspection
import com.intellij.codeInspection.InspectionToolProvider
import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.htmlInspections.*
import com.intellij.htmltools.codeInspection.htmlInspections.HtmlDeprecatedAttributeInspection
import com.intellij.htmltools.codeInspection.htmlInspections.HtmlDeprecatedTagInspection
import com.intellij.lang.javascript.inspections.*
import com.intellij.lang.typescript.inspections.TypeScriptUnresolvedFunctionInspection
import com.intellij.lang.typescript.inspections.TypeScriptUnresolvedVariableInspection
import com.intellij.lang.typescript.inspections.TypeScriptValidateTypesInspection
import com.intellij.xml.util.CheckDtdReferencesInspection
import com.intellij.xml.util.CheckEmptyTagInspection
import com.sixrr.inspectjs.validity.ThisExpressionReferencesGlobalObjectJSInspection

class AstroInspectionsProvider : InspectionToolProvider {
  override fun getInspectionClasses(): Array<Class<out LocalInspectionTool>> =
    arrayOf(
      HtmlDeprecatedAttributeInspection::class.java,
      HtmlDeprecatedTagInspection::class.java,
      HtmlExtraClosingTagInspection::class.java,
      HtmlUnknownBooleanAttributeInspection::class.java,
      HtmlUnknownAttributeInspection::class.java,
      HtmlUnknownTagInspection::class.java,
      HtmlWrongAttributeValueInspection::class.java,
      RequiredAttributesInspection::class.java,
      XmlUnboundNsPrefixInspection::class.java,
      CheckEmptyTagInspection::class.java,
      CheckDtdReferencesInspection::class.java,
      JSAnnotatorInspection::class.java,
      JSCheckFunctionSignaturesInspection::class.java,
      JSConstantReassignmentInspection::class.java,
      JSUnresolvedVariableInspection::class.java,
      JSUnresolvedFunctionInspection::class.java,
      JSUndeclaredVariableInspection::class.java,
      JSUnusedLocalSymbolsInspection::class.java,
      JSValidateTypesInspection::class.java,
      JSIncompatibleTypesComparisonInspection::class.java,
      ThisExpressionReferencesGlobalObjectJSInspection::class.java,
      TypeScriptValidateTypesInspection::class.java,
      TypeScriptUnresolvedVariableInspection::class.java,
      TypeScriptUnresolvedFunctionInspection::class.java
    )
}