/**
 ISAconfigurator is a component of the ISA software suite (http://www.isa-agents.org)

 License:
 ISAconfigurator is licensed under the Common Public Attribution License version 1.0 (CPAL)

 EXHIBIT A. CPAL version 1.0
 �The contents of this file are subject to the CPAL version 1.0 (the �License�); you may not use this file except
 in compliance with the License. You may obtain a copy of the License at http://isa-agents.org/licenses/ISAconfigurator-license.html.
 The License is based on the Mozilla Public License version 1.1 but Sections 14 and 15 have been added to cover use of software over
 a computer network and provide for limited attribution for the Original Developer. In addition, Exhibit A has been modified to be
 consistent with Exhibit B.

 Software distributed under the License is distributed on an �AS IS� basis, WITHOUT WARRANTY OF ANY KIND, either express
 or implied. See the License for the specific language governing rights and limitations under the License.

 The Original Code is ISAconfigurator.
 The Original Developer is the Initial Developer. The Initial Developer of the Original Code is the ISA Team
 (Eamonn Maguire, eamonnmag@gmail.com; Philippe Rocca-Serra, proccaserra@gmail.com; Susanna-Assunta Sansone,
 sa.sanson@gmail.com; http://www.isa-agents.org). All portions of the code written by the ISA Team are
 Copyright (c) 2007-2011 ISA Team. All Rights Reserved.

 EXHIBIT B. Attribution Information
 Attribution Copyright Notice: Copyright (c) 2008-2011 ISA Team
 Attribution Phrase: Developed by the ISA Team
 Attribution URL: http://www.isa-agents.org
 Graphic Image provided in the Covered Code as file: http://isa-agents.org/licenses/icons/poweredByISAagents.png
 Display of Attribution Information is required in Larger Works which are defined in the CPAL as a work which combines
 Covered Code or portions thereof with code not governed by the terms of the CPAL.

 Sponsors:
 The ISA Team and the ISA software suite have been funded by the EU Carcinogenomics project (http://www.carcinogenomics.eu),
 the UK BBSRC (http://www.bbsrc.ac.uk), the UK NERC-NEBC (http://nebc.nerc.ac.uk) and in part by the EU NuGO consortium
 (http://www.nugo.org/everyone).
 */

package org.isaagents.isacreatorconfigurator.configui;

import org.isaagents.isacreator.common.UIHelper;
import org.isaagents.isacreator.configuration.AssayTypes;
import org.isaagents.isacreator.configuration.DataTypes;
import org.isaagents.isacreator.configuration.DispatchTargets;
import org.isaagents.isacreator.configuration.FieldObject;
import org.isaagents.isacreator.effects.components.RoundedJTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


/**
 * AddTableGUI provides the interface for users to use when adding a new table to the
 * ISAcreators definitions.
 *
 * @author Eamonn Maguire
 */
public class AddTableGUI extends JDialog {
    private static final String MEASEND_DEFAULT = "";
    private static final String TECHTYPE_DEFAULT = "";
    private static final String SOURCE_DEFAULT = "";
    private static final String ACCESSION_DEFAULT = "";

    private DataEntryPanel parentGUI;
    private JLabel status;
    private JPanel assayDefPanel;
    private JLabel measEndLab;
    private JTextField measEndText;
    private JLabel measEndSourceLab;
    private JTextField measEndSourceText;
    private JLabel measEndAccessionLab;
    private JTextField measEndAccessionText;
    private JLabel techTypeLab;
    private JTextField techTypeText;
    private JLabel techSourceLab;
    private JTextField techSourceText;
    private JLabel techAccessionLab;
    private JTextField techAccessionText;
    private JLabel assayTypeLab;
    private JComboBox assayType;
    private JComboBox targetDispatch;
    private JComboBox typeCombo;
    private JTextField refNameText;

    /**
     * AddTableGUI
     * GUI required to add a table to the ISAcreator configuration.
     *
     * @param parentGUI - the MainMenu component this utility will appear in.
     */
    public AddTableGUI(DataEntryPanel parentGUI) {
        this.parentGUI = parentGUI;
        setBackground(UIHelper.BG_COLOR);

        createGUI();
        pack();
    }

    /**
     * createGUI method creates the GUI for the the AddTableGUI component.
     */
    private void createGUI() {
        JPanel headerPanel = new JPanel(new GridLayout(1, 1));
        headerPanel.setBackground(UIHelper.BG_COLOR);

        JLabel header = new JLabel(new ImageIcon(getClass()
                .getResource("/images/general_gui/defineTable.png")),
                JLabel.RIGHT);
        header.setOpaque(false);
        headerPanel.add(header);
        add(headerPanel, BorderLayout.NORTH);

        JPanel container = new JPanel();
        container.setBackground(UIHelper.BG_COLOR);

        status = UIHelper.createLabel("", UIHelper.VER_12_PLAIN, UIHelper.RED_COLOR);
        status.setHorizontalAlignment(SwingConstants.LEFT);
        status.setVerticalAlignment(SwingConstants.TOP);
        status.setPreferredSize(new Dimension(150, 50));

        assayDefPanel = new JPanel();
        assayDefPanel.setLayout(new BoxLayout(assayDefPanel, BoxLayout.PAGE_AXIS));
        assayDefPanel.setEnabled(false);
        assayDefPanel.setBackground(UIHelper.BG_COLOR);

        measEndLab = UIHelper.createLabel("measurement type :",
                UIHelper.VER_11_BOLD, UIHelper.GREY_COLOR);
        measEndLab.setEnabled(false);

        measEndText = new RoundedJTextField(10);
        measEndText.setText(MEASEND_DEFAULT);
        measEndText.setAgentTipText("<html>The <strong>type of measurement made</strong> with this assay, e.g. Gene Expression </html>");
        UIHelper.renderComponent(measEndText, UIHelper.VER_11_BOLD, UIHelper.GREY_COLOR, false);
        measEndText.setEnabled(false);

        measEndSourceLab = UIHelper.createLabel("term source :",
                UIHelper.VER_11_PLAIN, UIHelper.LIGHT_GREEN_COLOR);
        measEndSourceLab.setEnabled(false);

        measEndSourceText = new RoundedJTextField(10);
        measEndSourceText.setText(SOURCE_DEFAULT);
        measEndSourceText.setAgentTipText("<html>The <strong>ontology source</strong> with this measurement term, e.g. OBI </html>");
        UIHelper.renderComponent(measEndSourceText, UIHelper.VER_11_PLAIN, UIHelper.LIGHT_GREY_COLOR, false);
        measEndSourceText.setEnabled(false);

        measEndAccessionLab = UIHelper.createLabel("term accession :",
                UIHelper.VER_11_PLAIN, UIHelper.LIGHT_GREEN_COLOR);
        measEndAccessionLab.setEnabled(false);

        measEndAccessionText = new RoundedJTextField(10);
        measEndAccessionText.setText(ACCESSION_DEFAULT);
        measEndAccessionText.setAgentTipText("<html>The <strong>ontology accession</strong> with this measurement term, e.g. 12345 </html>");

        UIHelper.renderComponent(measEndAccessionText, UIHelper.VER_11_PLAIN, UIHelper.LIGHT_GREY_COLOR, false);
        measEndAccessionText.setEnabled(false);

        techTypeLab = UIHelper.createLabel("technology type :",
                UIHelper.VER_11_BOLD, UIHelper.GREY_COLOR);
        techTypeLab.setEnabled(false);

        techTypeText = new RoundedJTextField(10);
        techTypeText.setText(TECHTYPE_DEFAULT);
        techTypeText.setAgentTipText("<html>The <strong>type of technology used made</strong> with this assay, e.g. e.g. DNA microarray</html>");
        UIHelper.renderComponent(techTypeText, UIHelper.VER_11_BOLD, UIHelper.GREY_COLOR, false);
        techTypeText.setEnabled(false);

        techSourceLab = UIHelper.createLabel("term source ref :",
                UIHelper.VER_11_PLAIN, UIHelper.LIGHT_GREEN_COLOR);
        techSourceLab.setEnabled(false);

        techSourceText = new RoundedJTextField(10);
        techSourceText.setText(SOURCE_DEFAULT);
        techSourceText.setAgentTipText("<html>The <strong>ontology source</strong> with this technology term, e.g. OBI </html>");
        UIHelper.renderComponent(techSourceText, UIHelper.VER_11_PLAIN, UIHelper.LIGHT_GREY_COLOR, false);
        techSourceText.setEnabled(false);

        techAccessionLab = UIHelper.createLabel("term accession :",
                UIHelper.VER_11_PLAIN, UIHelper.LIGHT_GREEN_COLOR);
        techAccessionLab.setEnabled(false);

        techAccessionText = new RoundedJTextField(10);
        UIHelper.renderComponent(techAccessionText, UIHelper.VER_11_PLAIN, UIHelper.LIGHT_GREY_COLOR, false);
        techAccessionText.setText(ACCESSION_DEFAULT);
        techAccessionText.setAgentTipText("<html>The <strong>ontology accession</strong> with this technology term, e.g. 12345 </html>");

        techAccessionText.setEnabled(false);

        assayTypeLab = UIHelper.createLabel("assay type :",
                UIHelper.VER_11_BOLD, UIHelper.GREY_COLOR);
        assayTypeLab.setEnabled(false);

        assayType = new JComboBox(AssayTypes.asStringArray());
        UIHelper.setJComboBoxAsHeavyweight(assayType);
        UIHelper.renderComponent(assayType, UIHelper.VER_11_BOLD, UIHelper.GREY_COLOR, false);
        assayType.setEnabled(false);

        final JLabel targetDispatchLab = UIHelper.createLabel("conversion target :",
                UIHelper.VER_11_BOLD, UIHelper.GREY_COLOR);
        targetDispatchLab.setEnabled(false);

        targetDispatch = new JComboBox(DispatchTargets.asStringArray());
        UIHelper.setJComboBoxAsHeavyweight(targetDispatch);
        UIHelper.renderComponent(targetDispatch, UIHelper.VER_11_BOLD, UIHelper.GREY_COLOR, false);
        targetDispatch.setEnabled(false);


        JPanel fields = new JPanel();
        fields.setLayout(new BoxLayout(fields, BoxLayout.PAGE_AXIS));
        fields.setOpaque(false);

        JPanel tableType = new JPanel(new GridLayout(1, 2));
        tableType.setOpaque(false);

        JLabel selectTypeLab = UIHelper.createLabel("select type of table:", UIHelper.VER_11_BOLD, UIHelper.GREY_COLOR);

        tableType.add(selectTypeLab);

        typeCombo = new JComboBox(Location.values());
        UIHelper.setJComboBoxAsHeavyweight(typeCombo);
        typeCombo.setOpaque(false);
        typeCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                if (typeCombo.getSelectedIndex() == -1) {
                    typeCombo.setSelectedIndex(0);
                }

                boolean enabledFields = (typeCombo.getSelectedItem() == Location.ASSAY);

                measEndLab.setEnabled(enabledFields);
                measEndText.setEnabled(enabledFields);
                measEndSourceLab.setEnabled(enabledFields);
                measEndSourceText.setEnabled(enabledFields);
                measEndAccessionLab.setEnabled(enabledFields);
                measEndAccessionText.setEnabled(enabledFields);

                techTypeLab.setEnabled(enabledFields);
                techTypeText.setEnabled(enabledFields);
                techSourceLab.setEnabled(enabledFields);
                techSourceText.setEnabled(enabledFields);
                techAccessionLab.setEnabled(enabledFields);
                techAccessionText.setEnabled(enabledFields);

                assayTypeLab.setEnabled(enabledFields);
                assayType.setEnabled(enabledFields);
                targetDispatchLab.setEnabled(enabledFields);
                targetDispatch.setEnabled(enabledFields);
            }
        });
        UIHelper.renderComponent(typeCombo, UIHelper.VER_11_BOLD, UIHelper.GREY_COLOR, UIHelper.BG_COLOR);
        tableType.add(typeCombo);
        typeCombo.setSelectedIndex(0);

        fields.add(tableType);
        fields.add(Box.createVerticalStrut(5));


        //new location
        JPanel refNamePanel = new JPanel(new GridLayout(1, 2));
        refNamePanel.setBackground(UIHelper.BG_COLOR);

        JLabel refNameLab = UIHelper.createLabel("table name:", UIHelper.VER_11_BOLD, UIHelper.GREY_COLOR);
        refNamePanel.add(refNameLab);

        refNameText = new RoundedJTextField(10);
        UIHelper.renderComponent(refNameText, UIHelper.VER_12_PLAIN, UIHelper.GREY_COLOR, false);

        refNamePanel.add(refNameText);

        fields.add(refNamePanel);
        fields.add(Box.createVerticalStrut(5));


        // define measurement/endpoint panel
        JPanel measEndPanel = new JPanel(new GridLayout(3, 2));
        measEndPanel.setBackground(UIHelper.BG_COLOR);
        measEndPanel.setOpaque(false);
        measEndPanel.add(measEndLab);
        measEndPanel.add(measEndText);
        measEndPanel.add(measEndSourceLab);
        measEndPanel.add(measEndSourceText);
        measEndPanel.add(measEndAccessionLab);
        measEndPanel.add(measEndAccessionText);
        assayDefPanel.add(measEndPanel);
        assayDefPanel.add(Box.createVerticalStrut(5));

        // define technologytype panel
        JPanel techTypePanel = new JPanel(new GridLayout(3, 2));
        techTypePanel.setOpaque(false);
        techTypePanel.add(techTypeLab);
        techTypePanel.add(techTypeText);
        techTypePanel.add(techSourceLab);
        techTypePanel.add(techSourceText);
        techTypePanel.add(techAccessionLab);
        techTypePanel.add(techAccessionText);
        assayDefPanel.add(techTypePanel);

        JPanel assaySettingsPanel = new JPanel(new GridLayout(2, 2));
        assaySettingsPanel.setBackground(UIHelper.BG_COLOR);
        assaySettingsPanel.setOpaque(false);
        assaySettingsPanel.add(assayTypeLab);
        assaySettingsPanel.add(assayType);
        assaySettingsPanel.add(targetDispatchLab);
        assaySettingsPanel.add(targetDispatch);

        assayDefPanel.add(assaySettingsPanel);

        fields.add(assayDefPanel);
        fields.add(Box.createVerticalStrut(5));

        container.add(fields, BorderLayout.CENTER);

        add(container, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
        southPanel.setBackground(UIHelper.BG_COLOR);
        southPanel.add(UIHelper.wrapComponentInPanel(status));
        southPanel.add(Box.createVerticalStrut(5));

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.setBackground(UIHelper.BG_COLOR);

        JLabel cancel = new JLabel(new ImageIcon(getClass()
                .getResource("/images/general_gui/cancel.png")),
                JLabel.LEFT);
        cancel.setOpaque(false);
        cancel.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent event) {
                parentGUI.getApplicationContainer().hideSheet();
                measEndText.setText(MEASEND_DEFAULT);
                techTypeText.setText(TECHTYPE_DEFAULT);
                refNameText.setText("");
                status.setText("<html><p></p><p></p></html>");
            }
        });

        JLabel add = new JLabel(new ImageIcon(getClass()
                .getResource("/images/general_gui/addtablebutton.png")),
                JLabel.RIGHT);
        add.setOpaque(false);
        add.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent event) {
                addTable();
            }
        });

        buttonPanel.add(cancel);
        buttonPanel.add(add);

        southPanel.add(buttonPanel);
        southPanel.add(Box.createVerticalGlue());

        add(southPanel, BorderLayout.SOUTH);
    }

    private void addTable() {
        String measType = "n/a";
        String techType = "n/a";

        if (typeCombo.getSelectedItem() != Location.STUDY_SAMPLE && typeCombo.getSelectedItem() != Location.INVESTIGATION) {
            measType = (measEndText.getText().equals(MEASEND_DEFAULT))
                    ? "" : measEndText.getText();
            techType = (techTypeText.getText()
                    .equals(TECHTYPE_DEFAULT)) ? ""
                    : techTypeText.getText();
        }

        if (measType.equals("")) {
            status.setText(
                    "<html><b>Table not added!</b> <p> Required fields not entered!</p></html>");
            status.setForeground(UIHelper.RED_COLOR);

            return;
        }

        if (refNameText.getText().trim().equals("")) {
            status.setText(
                    "<html><b>Invalid table name!</b> <p> Table name is blank!</p></html>");
            status.setForeground(UIHelper.RED_COLOR);

            return;
        }

        List<FieldObject> initialFieldsList = null;

        // populate initial fields with the default fields
        Fields fieldList = parentGUI.getStandardISAFields();

        List<String> defaultFields = fieldList.getDefaultFieldsByLocation(Location.resolveLocationIdentifier(typeCombo.getSelectedItem().toString()));
        
        if (defaultFields.size() > 0) {
            initialFieldsList = new ArrayList<FieldObject>();

            for (String fieldName : defaultFields) {
                String sectionName = "";
                if(fieldName.lastIndexOf(":") > -1) {
                    String[] fieldInfo = fieldName.split(":");
                    fieldName = fieldInfo[0];
                    sectionName = fieldInfo[1];
                }
                FieldObject tfo = new FieldObject(fieldName, "", DataTypes.STRING, "", true, false, false);
                tfo.setSection(sectionName);
                tfo.setWizardTemplate(fieldList.getDefaultWizardTemplateForField(fieldName));
                initialFieldsList.add(tfo);
            }
        }

        String assayTypeValue = (typeCombo.getSelectedItem() == Location.STUDY_SAMPLE || typeCombo.getSelectedItem() == Location.INVESTIGATION)
                ? "" : assayType.getSelectedItem().toString();

        String dispatchTargetValue = (typeCombo.getSelectedItem() == Location.STUDY_SAMPLE || typeCombo.getSelectedItem() == Location.INVESTIGATION)
                ? "" : targetDispatch.getSelectedItem().toString();

        boolean added = parentGUI.addTable(((Location) typeCombo.getSelectedItem()).getType(),
                measType, measEndSourceText.getText(), measEndAccessionText.getText(), techType, techSourceText.getText(), techAccessionText.getText(),
                refNameText.getText(), initialFieldsList, assayTypeValue, dispatchTargetValue);

        if (!added) {
            status.setText(
                    "<html><b>Table not added!</b> <p> Check name and properties!</p></html>");
            status.setForeground(UIHelper.RED_COLOR);

        } else {
            status.setText("");
            status.setForeground(UIHelper.DARK_GREEN_COLOR);
            parentGUI.getApplicationContainer().hideSheet();
        }
    }

}