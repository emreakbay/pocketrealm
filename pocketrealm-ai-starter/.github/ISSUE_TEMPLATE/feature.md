name: Feature
description: Propose a product or engineering feature
title: "[Feature] "
labels: [feature]
body:
  - type: textarea
    id: problem
    attributes:
      label: Problem
      description: What user problem are we solving?
    validations:
      required: true
  - type: textarea
    id: proposal
    attributes:
      label: Proposal
      description: Describe the smallest useful solution.
    validations:
      required: true
  - type: textarea
    id: acceptance
    attributes:
      label: Acceptance criteria
    validations:
      required: true
