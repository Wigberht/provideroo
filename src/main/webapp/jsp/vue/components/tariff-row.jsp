<script type="text/x-template" id="tariff-row-template">
    <%--<tr>--%>
        <td>{{title}}</td>
        <td>{{description}}</td>
        <td>{{number_of_days}}</td>
        <td>{{cost}}</td>
        <td>{{currency}}</td>
    <%--</tr>--%>
</script>

<script>
    Vue.component('tariff-row', {
        props: {
            title: {type: String},
            description: {type: String},
            number_of_days: {type: String},
            cost: {type: String},
            currency: {type: String},
        },
        template: "#tariff-row-template",
        methods: {
            runCommand: function () {
                runCommand(this.command, this.method);
            }
        },
        mounted: function () {
            console.log("mounted to tariff row")
        }
    })
</script>